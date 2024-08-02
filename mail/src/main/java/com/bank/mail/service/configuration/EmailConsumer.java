package com.bank.mail.service.configuration;

import com.bank.mail.domain.EmailDTO;
import com.bank.mail.domain.EmailModel;
import com.bank.mail.service.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {

    public final EmailService service;

    public EmailConsumer(EmailService service) {
        this.service = service;
    }

    //Consome a mensagem enviada pelo login realizado
    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void listen(@Payload EmailDTO emailDTO){
        var emailModel = new EmailModel();
        BeanUtils.copyProperties(emailDTO, emailModel);
        service.sendEmail(emailModel);
    }
}
