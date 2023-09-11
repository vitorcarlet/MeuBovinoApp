package com.meubovinoapp.service;

import org.springframework.http.ResponseEntity;

public interface OxPriceService {
    ResponseEntity<String> getPriceById(Integer id);
}
