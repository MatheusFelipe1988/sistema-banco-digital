package com.felipe.picpay.service;

import com.felipe.picpay.client.AuthorizationClient;
import com.felipe.picpay.exception.BussinesException;
import com.felipe.picpay.model.dto.TransferDTO;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

    private final AuthorizationClient authorizationClient;

    public AuthorizationService(AuthorizationClient authorizationClient) {
        this.authorizationClient = authorizationClient;
    }

    public boolean isAuthorized(TransferDTO transfer){

        var response = authorizationClient.isAuthorized();

        if(response.getStatusCode().isError()){
            throw new BussinesException();
        }

        return response.getBody().authorized();
    }
}
