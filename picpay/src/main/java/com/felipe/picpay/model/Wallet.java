package com.felipe.picpay.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.felipe.picpay.model.dto.WalletDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "wallet")
@NoArgsConstructor
@Getter
@Setter
public class Wallet implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "cpf_cnpj", unique = true)
    private String cpfCnpj;

    @Column(name = "saldo")
    private BigDecimal saldo = BigDecimal.ZERO;

    @ManyToOne
    @JoinColumn(name = "walletType_id")
    @JsonIgnore
    private WalletType walletType;

    public Wallet(String name, String email, String password, String cpfCnpj, WalletType walletType) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.cpfCnpj = cpfCnpj;
        this.walletType = walletType;
    }

    public Wallet(long id, String name, String email, String password, String cpfCnpj,BigDecimal saldo, WalletType walletType) {
    }

    public Wallet(WalletDTO data) {
        this.name = data.name();
        this.email = data.email();
        this.password = data.password();
        this.cpfCnpj = data.cpfCnpj();
        this.walletType = data.walletType().get();
    }

    public boolean isTransferAllowedForWalletType() {
        return this.walletType.equals(WalletType.Enum.USER.get());
    }

    public boolean isBalancerEqualOrGreatherThan(BigDecimal value){
        return this.saldo.doubleValue() >= value.doubleValue();
    }

    public void debit(BigDecimal value){
        this.saldo = this.saldo.subtract(value);
    }

    public void credit(BigDecimal value){
        this.saldo = this.saldo.add(value);
    }
}
