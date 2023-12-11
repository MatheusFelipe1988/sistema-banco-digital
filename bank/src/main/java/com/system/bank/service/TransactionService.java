package com.system.bank.service;

import com.system.bank.DTOs.TransactionDTO;
import com.system.bank.domain.transaction.Transacao;
import com.system.bank.domain.user.User;
import com.system.bank.repositorio.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransactionService {
    @Autowired
    private UserService userService;
    @Autowired
    private TransactionRepository repository;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NotificationService notificationService;

    public Transacao createTransaction(TransactionDTO transaction) throws Exception {
        User sender = this.userService.findUserById(transaction.senderID());
        User receiver = this.userService.findUserById(transaction.receiverID());

        userService.validadeTransaction(sender, transaction.valor());

        boolean isAuthorized = this.authorizeTransaction(sender, transaction.valor());
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
    public boolean authorizeTransaction(User sender, BigDecimal valor){
      ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity("https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc",Map.class);

      if (authorizationResponse.getStatusCode() == HttpStatus.OK){
          String message = (String) authorizationResponse.getBody().get("message");
          return "Autorizado".equalsIgnoreCase(message);
      } else return false;
    }
}
