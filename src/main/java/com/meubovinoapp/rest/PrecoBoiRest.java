package com.meubovinoapp.rest;

import com.meubovinoapp.wrapper.PrecoBoiWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/preco")
public interface PrecoBoiRest {

    @GetMapping (path="/count")
    ResponseEntity<String> countPrices();

    @GetMapping (path="/get")
    ResponseEntity<String> getAveragePrice();

    @PostMapping(path="/add")
    ResponseEntity<String> addPrice(@RequestBody(required = true) Map<String, String> requestMap);

    @GetMapping (path="/getAll")
    ResponseEntity<List<PrecoBoiWrapper>> getAllPrices();

    @GetMapping(path="/getLast")
    ResponseEntity<PrecoBoiWrapper> setFirstResult();

}
