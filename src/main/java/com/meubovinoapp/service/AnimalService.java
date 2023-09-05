package com.meubovinoapp.service;

import com.meubovinoapp.POJO.Animal;
import com.meubovinoapp.wrapper.AnimalWrapper;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface AnimalService {

    ResponseEntity<Animal> findAnimalById(Integer id);

    ResponseEntity<List<AnimalWrapper>> getAllAnimals();


    ResponseEntity<String> addNewWeight(Map<String,String> requestMap);

    ResponseEntity<String> addAnimal(Map<String,String> requestMap);

    ResponseEntity<String> removeAnimal(Map<String,String> requestMap);


    ResponseEntity<String> updateAnimal(Map<String, String> requestMap);

    ResponseEntity<String> deleteProduct(Map<String, String> requestMap);
}
