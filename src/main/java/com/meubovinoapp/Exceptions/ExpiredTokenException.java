package com.meubovinoapp.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ExpiredTokenException extends RuntimeException {
    public ExpiredTokenException(String message){
        super("Token Expired");
        //System.out.println("token expired");
    }
}
