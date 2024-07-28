package com.felipe.picpay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class NotFoundException extends BussinesException{

    private Long walletId;

    public NotFoundException(Long walletId) {
        this.walletId = walletId;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var problemD = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

        problemD.setTitle("Wallet not found");
        problemD.setDetail("There is not wallet with id");

        return problemD;
    }
}
