package com.system.bank.repositorio;

import com.system.bank.domain.transaction.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transacao, Long> {

}
