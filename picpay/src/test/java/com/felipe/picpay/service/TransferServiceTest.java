package com.felipe.picpay.service;


import com.felipe.picpay.model.Wallet;
import com.felipe.picpay.model.WalletType;
import com.felipe.picpay.model.dto.TransferDTO;
import com.felipe.picpay.repository.TransferRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
    void createTransferCase1() throws Exception{
        Wallet sender = new Wallet(1L,"Max Verstappen", "max.com","12345","12345",
                new BigDecimal(10), WalletType.Enum.USER.get());
        Wallet receiver = new Wallet(1L,"Lewis Hamilton", "lh.com","123456","123456",
                new BigDecimal(20), WalletType.Enum.USER.get());

        when(walletService.findWalletById(1L)).thenReturn(sender);
        when(walletService.findWalletById(2L)).thenReturn(receiver);

        when(authorizationService.isAuthorized(any())).thenReturn(false);

        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            TransferDTO request = new TransferDTO(new BigDecimal(20), 1L, 2L);
            service.transfeer(request);
        });

        Assertions.assertEquals("Transaction not authorize", thrown.getMessage());
    }

    @Test
    void createTransferCase2() throws Exception {

        Wallet sender = new Wallet(1L,"Max Verstappen", "max.com","12345","12345",
                new BigDecimal(10), WalletType.Enum.USER.get());

        Wallet receiver = new Wallet(1L,"Lewis Hamilton", "lh.com","123456","123456",
                new BigDecimal(20), WalletType.Enum.USER.get());

        when(walletService.findWalletById(1L)).thenReturn(sender);
        when(walletService.findWalletById(2L)).thenReturn(receiver);

        when(authorizationService.isAuthorized(any())).thenReturn(true);

        TransferDTO request = new TransferDTO(new BigDecimal(20),1L,2L);
        service.transfeer(request);

        verify(repository,times(1)).save(any());

        sender.setSaldo(new BigDecimal(0));
        verify(walletService,times(1)).saveWallet(sender);

        receiver.setSaldo(new BigDecimal(20));
        verify(walletService,times(1)).saveWallet(receiver);

        verify(notificationService,times(1)).postNotification(service,"Transaction Success");
        verify(notificationService,times(1)).postNotification(service, "Transaction Fail");
    }

}
