package com.meubovinoapp.rest;

import com.meubovinoapp.POJO.MonthlyData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/monthlyOxWeight")
public interface MonthlyRest {

    @GetMapping (path="/get")
    ResponseEntity<List<MonthlyData>> getMonthlyWeight();

}
