package com.felipe.picpay.repository;

import com.felipe.picpay.model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface TransferRepository extends JpaRepository<Transfer, UUID> {
}
