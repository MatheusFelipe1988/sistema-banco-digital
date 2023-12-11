package com.system.bank.domain.user;

import com.system.bank.DTOs.UserDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity(name = "users")
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String sobreNome;
    @Column(unique = true)
    private String documento;
    @Column(unique = true)
    private String email;
    private String senha;
    private BigDecimal saldo;
    @Enumerated(EnumType.STRING)
    private UserType userType;

    public User(UserDTO data){
        this.nome = data.nome();
        this.sobreNome = data.sobreNome();
        this.documento = data.documento();
        this.saldo = data.saldo();
        this.email = data.email();
        this.userType = data.userType();
        this.senha = data.senha();
    }
}
