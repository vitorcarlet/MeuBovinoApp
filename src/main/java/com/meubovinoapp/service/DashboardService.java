package com.meubovinoapp.service;

import com.meubovinoapp.wrapper.AnimalWrapper;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface DashboardService {
    ResponseEntity<String> countAnimals(Integer user_Id_fk);

    ResponseEntity<String> calculateAverageWeight(Integer user_Id_fk);

    ResponseEntity<List<AnimalWrapper>> getAllAnimals();


}

