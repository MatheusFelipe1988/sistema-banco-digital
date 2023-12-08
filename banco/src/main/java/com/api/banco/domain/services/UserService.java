package com.api.banco.domain.services;

import com.api.banco.domain.user.User;
import com.api.banco.domain.user.userTyper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private  UserRepository repository;

    public User validateTransactio(User sender, BigDecimal amount) throws Exception {
        if(sender.getUserTyper() == userTyper.COMMON){
            throw new Exception("Voce lojista está impossibilitade de realizar transação");
        }

        if(sender.getSaldo().compareTo(amount) < 0 ){
            throw new Exception("Saldo Insuficiente");
        }

        //public User findUserById(Long id) throw Exception{
          //  return this.repository.findUserById(id).orElseThrow(() -> new Exception("usuario nao encontrado"));

        //public User findUserById(Long id){
          //  return this.repository.findUserById(id);
        //}

        public User findUserById(Long id){
            return this.repository.findUserById(id);
        }

        //public void saveUser(User user){
         //  this.repository.save(user);


    }
}
