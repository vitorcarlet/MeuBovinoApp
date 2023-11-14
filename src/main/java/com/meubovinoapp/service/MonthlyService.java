package com.meubovinoapp.service;

import com.meubovinoapp.POJO.MonthlyData;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MonthlyService {


      ResponseEntity<List<MonthlyData>> getMonthlyWeight();


}
