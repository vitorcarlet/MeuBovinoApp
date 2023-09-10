package com.meubovinoapp.service;

import com.meubovinoapp.POJO.Animal;
import com.meubovinoapp.POJO.Evolution;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

public interface EvolutionService {
     ResponseEntity<List<Evolution>> getAllDates(Integer animalId);

      ResponseEntity<String> addEvolution(Animal animalId, int weight, Date date);

     ResponseEntity<String> removeAllEvolutions(Integer id);

    }
