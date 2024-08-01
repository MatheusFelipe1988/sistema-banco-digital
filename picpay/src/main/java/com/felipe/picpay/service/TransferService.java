package com.felipe.picpay.service;

import com.felipe.picpay.exception.BalanceException;
import com.felipe.picpay.exception.NotFoundException;
import com.felipe.picpay.exception.TransferNotAllowedToWalletTypeException;
import com.felipe.picpay.exception.TransferNotAuthorizeException;
import com.felipe.picpay.model.Transfer;
import com.felipe.picpay.model.Wallet;
import com.felipe.picpay.model.dto.TransferDTO;
import com.felipe.picpay.repository.TransferRepository;
import com.felipe.picpay.repository.WalletRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class TransferService {

    private final TransferRepository repository;
    private final WalletRepository walletRepository;
    private final NotificationService service;
    private final AuthorizationService authorizationService;


    public TransferService(TransferRepository repository, WalletRepository walletRepository, NotificationService service, AuthorizationService authorizationService) {
        this.repository = repository;
        this.walletRepository = walletRepository;
        this.service = service;
        this.authorizationService = authorizationService;
    }

    @Transactional
    public Transfer transfer(TransferDTO transferDTO){

        var sender = walletRepository.findById(transferDTO.payer())
                .orElseThrow(() -> new NotFoundException(transferDTO.payer()));

        var receiver = walletRepository.findById(transferDTO.payee())
                .orElseThrow(() -> new NotFoundException(transferDTO.payee()));

        validateTransfer(transferDTO, sender);

        sender.debit(transferDTO.value());
        receiver.credit(transferDTO.value());

        var transfer = new Transfer(sender, receiver, transferDTO.value());

        walletRepository.save(sender);
        walletRepository.save(receiver);

        var transferResult = repository.save(transfer);

        CompletableFuture.runAsync(() -> service.sendNotificaion(transferResult));

        return transferResult;

    }

    private void validateTransfer(TransferDTO transferDTO, Wallet sender) {

        if(!sender.isTransferAllowedForWalletType()){
            throw new TransferNotAllowedToWalletTypeException();
        }
        if (!sender.isBalancerEqualOrGreatherThan(transferDTO.value())){
            throw new BalanceException();
        }
        if (!authorizationService.isAuthorized(transferDTO)){
            throw new TransferNotAuthorizeException();
        }
    }
}
