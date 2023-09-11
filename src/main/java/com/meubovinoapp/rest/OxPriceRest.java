package com.meubovinoapp.rest;

import com.meubovinoapp.POJO.Animal;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping(path = "/oxprice")
public interface OxPriceRest {

    @GetMapping(path = "/{id}")
    public ResponseEntity<String> getPriceById(@PathVariable Integer id);



}
