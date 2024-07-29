package com.felipe.picpay.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "wallet")
@NoArgsConstructor
@Getter
@Setter
public class Wallet {

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
