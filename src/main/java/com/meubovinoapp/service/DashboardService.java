package com.meubovinoapp.service;

import com.meubovinoapp.wrapper.AnimalWrapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

public interface DashboardService {
    ResponseEntity<String> countAnimals(Integer user_Id_fk);

    ResponseEntity<String> calculateAverageWeight(Integer user_Id_fk);


    ResponseEntity<Page<AnimalWrapper>> getAllAnimals(Pageable pageable);
}

