package com.meubovinoapp.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(path= "/dashboard")
public interface DashboardRest {

    @GetMapping(path = "/count")
    ResponseEntity<String> getCount(@PathVariable Integer userId_fk);

    @GetMapping(path = "/weight")
    ResponseEntity<String> getAverageWeight(@PathVariable Integer userId_fk);

}
