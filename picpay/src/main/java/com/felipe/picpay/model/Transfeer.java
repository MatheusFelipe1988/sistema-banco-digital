package com.felipe.picpay.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "transfer")
@Getter
@Setter
@NoArgsConstructor
public class Transfeer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "wallet_sende_id")
    private Wallet sender;

    @ManyToOne
    @JoinColumn(name = "wallet_reciever_id")
    private Wallet receiver;

    @Column(name = "value")
    private BigDecimal value;

    public Transfeer(Wallet sender, Wallet receiver, BigDecimal value) {
        this.sender = sender;
        this.receiver = receiver;
        this.value = value;
    }
}
