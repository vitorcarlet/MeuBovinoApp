package com.meubovinoapp.rest;

import com.meubovinoapp.wrapper.AnimalWrapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

//@RestController
@RequestMapping(path= "/dashboard")
public interface DashboardRest {

    @GetMapping(path = "/count/{user_Id_fk}")
    ResponseEntity<String> countAnimals(@PathVariable Integer user_Id_fk);

    @GetMapping(path = "/weight/{user_Id_fk}")
    ResponseEntity<String> calculateAverageWeight(@PathVariable Integer user_Id_fk);

    @GetMapping(path = "/getAllAnimals")
    public ResponseEntity<Page<AnimalWrapper>> getAllAnimals(Pageable pageable);

}
