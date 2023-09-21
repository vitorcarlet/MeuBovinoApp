package com.meubovinoapp.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BovinoUtils {

    //Classe sem instância
    private BovinoUtils(){

    }

    public static ResponseEntity<String> getResponseEntity(String responseMessage, HttpStatus httpStatus){
        return new ResponseEntity<String>("{\"message\":\""+responseMessage+"\"}", httpStatus);
    }

}
