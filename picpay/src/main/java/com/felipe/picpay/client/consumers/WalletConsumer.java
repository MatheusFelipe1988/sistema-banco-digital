package com.felipe.picpay.client.consumers;

import com.felipe.picpay.model.Wallet;
import com.felipe.picpay.model.dto.WalletDTO;
import com.felipe.picpay.service.WalletService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class WalletConsumer {

    private final WalletService service;

    public WalletConsumer(WalletService service) {
        this.service = service;
    }

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void listen(@Payload WalletDTO walletDTO){
        Wallet wallet = new Wallet();
        BeanUtils.copyProperties(walletDTO, wallet);
        service.createWallet(walletDTO);
        System.out.println("Wallet Type: " + wallet.getWalletType().toString());
    }

    
}
