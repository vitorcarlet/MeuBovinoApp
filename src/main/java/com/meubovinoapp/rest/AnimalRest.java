package com.meubovinoapp.rest;

import com.meubovinoapp.wrapper.UserWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/animal")
public interface AnimalRest {

    @PostMapping(path = "/add")
    public ResponseEntity<String> addAnimal(@RequestBody(required = true) Map<String, String> requestMap);

    @GetMapping(path = "/get")
    public ResponseEntity<List<UserWrapper>> getAllAnimals();

    @PostMapping(path = "/update")
    public ResponseEntity<String> updateAnimal(@RequestBody(required = true) Map<String, String> requestMap);

    @PostMapping(path = "/addNewWeight")
    public ResponseEntity<String> addNewWeight(@RequestBody(required = true) Map<String, String> requestMap);

    @PostMapping(path = "/removeNewWeight")
    public ResponseEntity<String> removeNewWeight(@RequestBody(required = true) Map<String, String> requestMap);



    @GetMapping(path = "/checkToken")
    ResponseEntity<String> checkToken();

}