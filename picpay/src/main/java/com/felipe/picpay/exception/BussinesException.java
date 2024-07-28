package com.felipe.picpay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class BussinesException extends RuntimeException{
    public ProblemDetail toProblemDetail(){
        var problemD = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        problemD.setTitle("Internal Server Error");

        return problemD;
    }
}
