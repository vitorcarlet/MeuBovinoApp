package com.meubovinoapp.rest;

import com.meubovinoapp.POJO.Animal;
import com.meubovinoapp.wrapper.AnimalWrapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

//@RestController
@RequestMapping(path = "/animal")
public interface AnimalRest {

    @PostMapping(path = "/add")
    public ResponseEntity<String> addAnimal(@RequestBody(required = true) Map<String, String> requestMap);



    @GetMapping(path = "/get")
    ResponseEntity<Page<AnimalWrapper>> getAllAnimals(Pageable pageable, String name);

    @GetMapping(path = "/{id}")
    public ResponseEntity<AnimalWrapper> findById(@PathVariable String id);

    @PostMapping(path = "/update")
    public ResponseEntity<String> updateAnimal(@RequestBody(required = true) Map<String, String> requestMap);

    @PostMapping(path = "/remove")
    public ResponseEntity<String> deleteAnimal(@RequestBody(required = true) Map <String, String> requestMap);

    @PostMapping(path = "/addNewWeight")
    public ResponseEntity<String> addNewWeight(@RequestBody(required = true) Map<String, String> requestMap);



}