package com.felipe.picpay.service;

import com.felipe.picpay.exception.BussinesException;
import com.felipe.picpay.exception.CustomerDataAlreadyExistException;
import com.felipe.picpay.model.Wallet;
import com.felipe.picpay.model.dto.WalletDTO;
import com.felipe.picpay.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    private final WalletRepository repository;

    public WalletService(WalletRepository repository) {
        this.repository = repository;
    }

    public Wallet createWallet(WalletDTO walletDTO) {

        var walletDb = repository.findByCpfCnpjOrEmail(walletDTO.cpfCnpj(), walletDTO.email());

        if(walletDb.isPresent()){
            throw new CustomerDataAlreadyExistException("cpf, cnpj or email already exist");
        }

        return repository.save(walletDTO.toWallet());
    }

    public Wallet findWalletById(Long id) throws Exception {
        return this.repository.findWalletById(id).orElseThrow(() -> new Exception("User not found"));
    }

    public void saveWallet(Wallet wallet){
        this.repository.save(wallet);
    }
}
