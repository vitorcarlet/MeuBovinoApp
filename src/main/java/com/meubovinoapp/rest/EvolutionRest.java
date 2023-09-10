package com.meubovinoapp.rest;


import com.meubovinoapp.POJO.Evolution;
import com.meubovinoapp.wrapper.EvolutionWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

@RequestMapping(path= "/evolution")
public interface EvolutionRest {

    @GetMapping(path="/get/{animalName}")
    ResponseEntity<List<EvolutionWrapper>> getAllDates(@PathVariable String animalName);

}
