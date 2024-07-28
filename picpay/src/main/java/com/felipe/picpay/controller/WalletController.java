package com.felipe.picpay.controller;

import com.felipe.picpay.model.Wallet;
import com.felipe.picpay.model.dto.WalletDTO;
import com.felipe.picpay.service.WalletService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WalletController {

    private final WalletService service;

    public WalletController(WalletService service) {
        this.service = service;
    }

    @PostMapping("/wallets")
    public ResponseEntity<Wallet> createWallet(@RequestBody @Valid WalletDTO walletDTO){
        var wallet = service.createWallet(walletDTO);

        return ResponseEntity.ok(wallet);
    }
}
