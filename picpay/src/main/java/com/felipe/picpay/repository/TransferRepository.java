package com.felipe.picpay.repository;

import com.felipe.picpay.model.Transfeer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TransferRepository extends JpaRepository<Transfeer, Long> {
}
