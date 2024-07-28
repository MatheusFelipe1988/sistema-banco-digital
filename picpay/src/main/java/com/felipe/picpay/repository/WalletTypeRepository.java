package com.felipe.picpay.repository;

import com.felipe.picpay.model.WalletType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletTypeRepository extends JpaRepository<WalletType, Long> {
}
