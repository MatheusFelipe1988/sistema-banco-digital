package com.felipe.picpay.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "walletType")
@NoArgsConstructor
public class WalletType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    public WalletType(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    @AllArgsConstructor
    public enum Enum{

        USER(1L, "user"),
        MERCHANT(2L, "merchant");

        private Long id;
        private String description;

        public WalletType get(){
            return new WalletType(id, description);
        }
    }

    @Override
    public boolean equals(Object e){

        if(this == e) return true;
        if(e == null || getClass() != e.getClass()) return false;

        WalletType that = (WalletType) e;

        return Objects.equals(id, that.id) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode(){
        return Objects.hash(id, description);
    }

}
