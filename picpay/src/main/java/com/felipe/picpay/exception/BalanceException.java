package com.felipe.picpay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class BalanceException extends BussinesException{

    @Override
    public ProblemDetail toProblemDetail() {

        var problemD = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

        problemD.setTitle("Insufficient Balance");
        problemD.setDetail("You cannot transfer a value bigger than you current balance.");

        return problemD;
    }
}
