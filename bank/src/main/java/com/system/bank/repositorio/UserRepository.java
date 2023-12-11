package com.system.bank.repositorio;

import com.system.bank.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByDocumento(String documento);

    Optional<User> findUserById(Long id);
}
