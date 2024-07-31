package com.felipe.picpay.client.producer;

import com.felipe.picpay.model.Email;
import com.felipe.picpay.model.Wallet;
import com.felipe.picpay.model.dto.EmailDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WalletProducer {
    final RabbitTemplate rabbitTemplate;

    public WalletProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value(value = "${spring.rabbitmq.queue}")
    private String routingKey;

    public void publishMessageEmail(Email email){
        var emailDto = new EmailDTO();
        emailDto.setId(email.getId());
        emailDto.setEmailTo(email.getEmail());
        emailDto.setSubject("Success");
        emailDto.setText(email.getName() + ", O picpay agradece o seu cadastro em nosso sistema, aproveite as transferencias em nossa plataforma");

        rabbitTemplate.convertAndSend("", routingKey, emailDto);
    }
}
