/*
package com.felipe.picpay.repository;

import com.felipe.picpay.model.Wallet;
import com.felipe.picpay.model.WalletType;
import com.felipe.picpay.model.dto.WalletDTO;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

@DataJpaTest
@ActiveProfiles("test")
class WalletRepositoryTest {

    @Autowired
    private WalletRepository repository;

    @Autowired
    EntityManager entityManager;


    @Test
    void findByCpfOrEmailSuccess(){

        String email = "max.com";
        String cpf = "12345";

        WalletDTO walletDTO = new WalletDTO("Lewis Hamilton", "lh44.com","12345",
                "12345", WalletType.Enum.USER);
        this.createWallet(walletDTO);

        Optional<Wallet> result = this.repository.findByCpfCnpjOrEmail(email, cpf);

        assertThat(result.isPresent()).isTrue();
    }

    @Test
    @DisplayName("users not exist")
    void findByCpfOrEmailFail(){
        String cpf = "12345";
        String email = "max.com";

        Optional<Wallet> result = this.repository.findByCpfCnpjOrEmail(cpf, email);

        assertThat(result.isEmpty()).isTrue();


    }

    private Wallet createWallet(WalletDTO data){
        Wallet newWallet = new Wallet(data);
        this.entityManager.persist(newWallet);
        return newWallet;
    }
}

*/