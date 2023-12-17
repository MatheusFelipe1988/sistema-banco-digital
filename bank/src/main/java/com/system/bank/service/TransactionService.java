package com.system.bank.service;

import com.system.bank.DTOs.TransactionDTO;
import com.system.bank.domain.transaction.Transacao;
import com.system.bank.domain.user.User;
import com.system.bank.repositorio.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransactionService {
    @Autowired
    private UserService userService;
    @Autowired
    private TransactionRepository repository;
    @Autowired
    private AuthorizationService authservice;

    @Autowired
    private NotificationService notificationService;

    public Transacao createTransaction(TransactionDTO transaction) throws Exception {
        User sender = this.userService.findUserById(transaction.senderID());
        User receiver = this.userService.findUserById(transaction.receiverID());

        userService.validadeTransaction(sender, transaction.valor());

        boolean isAuthorized = this.authservice.authorizeTransaction(sender, transaction.valor());
        if (!isAuthorized){
            throw new Exception("Transação não autorizado");
        }
        Transacao newtransacao = new Transacao();
        newtransacao.setAmount(transaction.valor());
        newtransacao.setSender(sender);
        newtransacao.setTimestamp(LocalDateTime.now());

        sender.setSaldo(sender.getSaldo().subtract(transaction.valor()));
        receiver.setSaldo(receiver.getSaldo().add(transaction.valor()));

        this.repository.save(newtransacao);
        this.userService.saveUser(sender);
        this.userService.saveUser(receiver);

        this.notificationService.sendNotification(sender, "Transação realizada com sucesso");
        this.notificationService.sendNotification(receiver, "Transação recebida com sucesso");

        return newtransacao;

    }
}
