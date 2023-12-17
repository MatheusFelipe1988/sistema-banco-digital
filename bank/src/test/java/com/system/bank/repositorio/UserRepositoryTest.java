package com.system.bank.repositorio;

import com.system.bank.DTOs.UserDTO;
import com.system.bank.domain.user.User;
import com.system.bank.domain.user.UserType;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Optional;


@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("should get User sucesso from DB")
    void findUserByDocumentoSucesso() {
        String document = "lewis.pdf";
        UserDTO data = new UserDTO("lewis","hamilton", document, new BigDecimal(19), "lh44.com","44roscoe", UserType.COMMON);
        this.createUser(data);

        Optional<User> result = this.userRepository.findUserByDocumento(document);

        assertThat(result.isPresent()).isTrue();

    }
    @Test
    @DisplayName("users not exist")
    void findUserByDocumentoError() {
        String document = "lewis.pdf";

        Optional<User> result = this.userRepository.findUserByDocumento(document);

        assertThat(result.isEmpty()).isTrue();

    }

    private User createUser(UserDTO data){
        User newUse = new User(data);
        this.entityManager.persist(newUse);
        return newUse;

    }
}