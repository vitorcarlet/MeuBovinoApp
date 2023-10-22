package com.meubovinoapp.service;

import com.meubovinoapp.wrapper.PrecoBoiWrapper;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface PrecoBoiService {
      ResponseEntity<String> countPrices();


      ResponseEntity<String> getAveragePrice();


      ResponseEntity<List<PrecoBoiWrapper>> getAllPrices();

      ResponseEntity<String> addPrice(Map<String,String> requestMap);

      ResponseEntity<PrecoBoiWrapper> setFirstResult();
}
