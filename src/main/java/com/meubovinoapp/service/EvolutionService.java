package com.meubovinoapp.service;

import com.meubovinoapp.POJO.Animal;
import com.meubovinoapp.POJO.Evolution;
import com.meubovinoapp.wrapper.EvolutionWrapper;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface EvolutionService {
     ResponseEntity<List<EvolutionWrapper>> getAllDates(String animalName);

      ResponseEntity<String> addEvolution(Animal animalId, int weight, Date date);

     ResponseEntity<String> removeAllEvolutions(Integer id);

    ResponseEntity<String> editEvolution(Map<String, String> requestMap);

    ResponseEntity<String> removeEvolution(Integer id);
}
