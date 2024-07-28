package com.felipe.picpay.model.dto;

import com.felipe.picpay.model.Wallet;
import com.felipe.picpay.model.WalletType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record WalletDTO(@NotBlank String name, @NotBlank String email, @NotBlank String cpfCnpj,
                        @NotBlank String password, @NotNull WalletType.Enum walletType) {
    public Wallet toWallet(){
        return new Wallet(
                name,
                email,
                cpfCnpj,
                password,
                walletType.get()
        );
    }
}
