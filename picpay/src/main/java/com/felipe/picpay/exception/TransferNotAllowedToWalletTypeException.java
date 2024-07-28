package com.felipe.picpay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class TransferNotAllowedToWalletTypeException extends BussinesException{
    @Override
    public ProblemDetail toProblemDetail() {
        var problemD = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

        problemD.setTitle("This wallet type is not allowed to transfer");

        return problemD;
    }
}
