package com.meubovinoapp.rest;


import com.meubovinoapp.POJO.Evolution;
import com.meubovinoapp.wrapper.EvolutionWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RequestMapping(path= "/evolution")
public interface EvolutionRest {

    @GetMapping(path="/get/{animalName}")
     ResponseEntity<List<EvolutionWrapper>> getAllDates(@PathVariable String animalName);

    @PostMapping(path = "/edit")
    ResponseEntity<String> editEvolution(@RequestBody(required = true) Map<String, String> requestMap);

    @PostMapping(path = "/remove/{id}")
    ResponseEntity<String> removeEvolution(Integer id);


}
