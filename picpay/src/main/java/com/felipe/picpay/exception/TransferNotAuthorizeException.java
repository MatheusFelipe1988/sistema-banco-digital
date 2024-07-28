package com.felipe.picpay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class TransferNotAuthorizeException extends BussinesException{
    @Override
    public ProblemDetail toProblemDetail() {
        var problemD = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

        problemD.setTitle("Transfer not authorize");
        problemD.setDetail("Authorization service not authorized this transfer");

        return problemD;
    }
}
