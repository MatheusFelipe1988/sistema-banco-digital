package com.system.bank.service;

import com.system.bank.DTOs.UserDTO;
import com.system.bank.domain.user.User;
import com.system.bank.domain.user.UserType;
import com.system.bank.repositorio.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public void validadeTransaction(User sender, BigDecimal amount) throws Exception {
        if (sender.getUserType() == UserType.COMMON) {
            throw new Exception("Voce lojista está impossibilitade de realizar transação");
        }
        if(sender.getSaldo().compareTo(amount) < 0 ){
            throw new Exception("Saldo Insuficiente");
        }
    }
    public User findUserById(Long id) throws Exception {
        return this.repository.findUserById(id).orElseThrow(() -> new Exception("Usuario nao encontrado"));
    }

    public User createUser(UserDTO data){
        User newUser = new User(data);
        this.saveUser(newUser);
        return newUser;
    }
    public List<User> getAllUsers(){
        return this.repository.findAll();
    }

    public void saveUser(User user){
        this.repository.save(user);
    }
}
