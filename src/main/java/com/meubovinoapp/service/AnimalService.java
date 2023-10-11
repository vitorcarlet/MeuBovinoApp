package com.meubovinoapp.service;

import com.meubovinoapp.POJO.Animal;
import com.meubovinoapp.wrapper.AnimalWrapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface AnimalService {

    ResponseEntity<AnimalWrapper> findAnimalById(String id);



    ResponseEntity<String> addNewWeight(Map<String,String> requestMap);

    ResponseEntity<String> addAnimal(Map<String,String> requestMap);

   //ResponseEntity<String> removeAnimal(Map<String,String> requestMap);


    ResponseEntity<String> updateAnimal(Map<String, String> requestMap);

    ResponseEntity<String> deleteAnimal(Map<String, String> requestMap);

    ResponseEntity<Page<AnimalWrapper>> getAllAnimals(Pageable pageable);
}
