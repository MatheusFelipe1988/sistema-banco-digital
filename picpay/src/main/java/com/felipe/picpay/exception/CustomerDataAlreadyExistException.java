package com.felipe.picpay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class CustomerDataAlreadyExistException extends BussinesException{

    public String detail;

    public CustomerDataAlreadyExistException(String detail){
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {

        var problemDe = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

        problemDe.setTitle("Wallet data already exist");

        problemDe.setDetail(detail);

        return problemDe;
    }
}
