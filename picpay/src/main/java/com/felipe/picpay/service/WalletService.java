package com.felipe.picpay.service;

import com.felipe.picpay.client.producer.WalletProducer;
import com.felipe.picpay.exception.CustomerDataAlreadyExistException;
import com.felipe.picpay.model.Email;
import com.felipe.picpay.model.Wallet;
import com.felipe.picpay.model.dto.WalletDTO;
import com.felipe.picpay.repository.EmailRepository;
import com.felipe.picpay.repository.WalletRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class WalletService {
    private final WalletRepository repository;
    private final EmailRepository emailRepository;
    private final WalletProducer producer;

    public WalletService(WalletRepository repository, EmailRepository emailRepository, WalletProducer producer) {
        this.repository = repository;
        this.emailRepository = emailRepository;
        this.producer = producer;
    }


    /*
        Adiciona novo usuário sendo user e merchant
    */
    public Wallet createWallet(WalletDTO walletDTO) {
        var walletDb = repository.findByCpfCnpjOrEmail(walletDTO.cpfCnpj(), walletDTO.email());
        if(walletDb.isPresent()){
            throw new CustomerDataAlreadyExistException("cpf, cnpj or email already exist");
        }

        return repository.save(walletDTO.toWallet());
    }


    /*
        Busca o usuário pelo ID, método utilizado somente para testes unitários.
    */
    public Wallet findWalletById(Long id) throws Exception {
        return this.repository.findWalletById(id).orElseThrow(() -> new Exception("User not found"));
    }


    /*
        Com o mesmo propósito de adicionar usuários, mas exclusivo para testes unitários.
    */
    public void saveWallet(Wallet wallet){
        this.repository.save(wallet);
    }


    /*
        Envia um e-mail para confirmar o login.
    */
    @Transactional
    public Email saveEmail(Email email){
        email = emailRepository.save(email);
        producer.publishMessageEmail(email);
        return email;
    }
}