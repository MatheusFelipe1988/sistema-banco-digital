package com.felipe.picpay.service;


import org.mockito.Mock;

public class TransferServiceTest {

    @Mock
    public final TransferService service;

    public TransferServiceTest(TransferService service) {
        this.service = service;
    }
}
