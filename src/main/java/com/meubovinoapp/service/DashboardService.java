package com.meubovinoapp.service;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface DashboardService {
    ResponseEntity<String> getCount(Integer userId_fk);

    ResponseEntity<String> getAverageWeight(Integer userId_fk);
}

