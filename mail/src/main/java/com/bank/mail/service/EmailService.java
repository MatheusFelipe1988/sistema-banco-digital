package com.bank.mail.service;

import com.bank.mail.domain.EmailModel;
import com.bank.mail.domain.StatusEmail;
import com.bank.mail.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class EmailService {

    public final EmailRepository repository;

    public final JavaMailSender javaMailSender;

    public EmailService(EmailRepository repository, JavaMailSender javaMailSender) {
        this.repository = repository;
        this.javaMailSender = javaMailSender;
    }

    @Value("${MAIL_USER}")
    private String emailFrom;


    /*
        Envia o e-mail quando  o login é realizado
    */
    @Transactional
    @Cacheable("email")
    public EmailModel sendEmail(EmailModel emailModel){

        try {
            emailModel.setSendDataEmail(LocalDateTime.now());
            emailModel.setEmailFrom(emailFrom);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(emailModel.getEmailTo());
            message.setSubject(emailModel.getSubject());
            message.setText(emailModel.getText());

            javaMailSender.send(message);

            emailModel.setStatusEmail(StatusEmail.SENT);
        }catch (MailException e){
            emailModel.setStatusEmail(StatusEmail.ERROR);
        } finally {
            return repository.save(emailModel);
        }
    }
}
