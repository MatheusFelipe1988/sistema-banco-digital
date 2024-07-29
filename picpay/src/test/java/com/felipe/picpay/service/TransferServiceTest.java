package com.felipe.picpay.service;


import com.felipe.picpay.model.Wallet;
import com.felipe.picpay.model.WalletType;
import com.felipe.picpay.repository.TransferRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

class TransferServiceTest {

    @Mock
    private TransferRepository repository;

    @Mock
    private WalletService walletService;

    @Mock
    private AuthorizationService authorizationService;

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    @Autowired
    private TransferService service;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createTransferCase1(){
        Wallet sender = new Wallet(1L,"Max Verstappen", "max.com","12345","12345", new BigDecimal(10), WalletType.Enum.USER.get());
        Wallet receiver = new Wallet(1L,"Lewis Hamilton", "lh.com","123456","123456", new BigDecimal(20), WalletType.Enum.USER.get());
    }
    
}
