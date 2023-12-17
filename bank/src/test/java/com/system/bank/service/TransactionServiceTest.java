package com.system.bank.service;

import com.system.bank.DTOs.TransactionDTO;
import com.system.bank.domain.user.User;
import com.system.bank.domain.user.UserType;
import com.system.bank.repositorio.TransactionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TransactionServiceTest {
    @Mock
    private UserService userService;
    @Mock
    private TransactionRepository repository;
    @Mock
    private AuthorizationService authservice;

    @Mock
    private NotificationService notificationService;

    @Autowired
    @InjectMocks
    private TransactionService transactionService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Should create transaction sucess everything when is OK")
    void createTransactionCase1() throws Exception {
        User sender = new User(1L,"jos","Verspa","jos.docx","jos.com","3W",new BigDecimal(10), UserType.COMMON);
        User receiver = new User(2L,"Sebastian","Vettel","vettel.docx","sebastian.com","5D",new BigDecimal(10), UserType.COMMON);

        when(userService.findUserById(1L)).thenReturn(sender);
        when(userService.findUserById(2L)).thenReturn(receiver);

        when(authservice.authorizeTransaction(any(),any())).thenReturn(true);

        TransactionDTO request = new TransactionDTO(new BigDecimal(20),1L,2L);
        transactionService.createTransaction(request);

        verify(repository,times(1)).save(any());

        sender.setSaldo(new BigDecimal(0));
        verify(userService,times(1)).saveUser(sender);

        receiver.setSaldo(new BigDecimal(20));
        verify(userService,times(1)).saveUser(receiver);

        verify(notificationService,times(1)).sendNotification(sender, "Transação realizada com sucesso");
        verify(notificationService,times(1)).sendNotification(receiver, "Transação recebida com sucesso");


    }
    @Test
    @DisplayName("Should throw Exception when transaction is not allowed")
    void createTransactionCase2() throws Exception {

        User sender = new User(1L,"jos","Verspa","jos.docx","jos.com","3W",new BigDecimal(10), UserType.COMMON);
        User receiver = new User(2L,"Sebastian","Vettel","vettel.docx","sebastian.com","5D",new BigDecimal(10), UserType.COMMON);

        when(userService.findUserById(1L)).thenReturn(sender);
        when(userService.findUserById(2L)).thenReturn(receiver);

        when(authservice.authorizeTransaction(any(),any())).thenReturn(false);

        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            TransactionDTO request = new TransactionDTO(new BigDecimal(20),1L,2L);
            transactionService.createTransaction(request);
        });

        Assertions.assertEquals("Transação não autorizado", thrown.getMessage());

    }
}