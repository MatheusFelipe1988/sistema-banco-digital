package com.felipe.picpay.controller;

import com.felipe.picpay.model.Wallet;
import com.felipe.picpay.model.dto.WalletDTO;
import com.felipe.picpay.service.WalletService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Usuário", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Para novo tipo de usuário"),
            @ApiResponse(responseCode = "400", description = "Existencia pelo CPF, CNPJ ou email")
    })
    @PostMapping("/wallets")
    public ResponseEntity<Wallet> createWallet(@RequestBody @Valid WalletDTO walletDTO){
        var wallet = service.createWallet(walletDTO);

        return ResponseEntity.ok(wallet);
    }
}
