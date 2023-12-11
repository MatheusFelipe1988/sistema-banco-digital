package com.system.bank.DTOs;

import com.system.bank.domain.user.UserType;

import java.math.BigDecimal;

public record UserDTO(String nome, String sobreNome, String documento, BigDecimal saldo, String email, String senha, UserType userType) {
}
