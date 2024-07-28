package com.felipe.picpay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.felipe.picpay.model.Wallet;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Optional<Wallet> findByCpfCnpjOrEmail(String cpfCnpj, String email);
}
