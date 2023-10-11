package com.meubovinoapp.restImpl;

import com.meubovinoapp.POJO.Animal;
import com.meubovinoapp.constants.BovinoConstants;
import com.meubovinoapp.rest.AnimalRest;
import com.meubovinoapp.service.AnimalService;
import com.meubovinoapp.utils.BovinoUtils;
import com.meubovinoapp.wrapper.AnimalWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Map;

@RestController
public class AnimalRestImpl implements AnimalRest {

    @Autowired
    AnimalService animalService;

    @Override
    public ResponseEntity<String> addAnimal(Map<String, String> requestMap) {
        try{
           return  animalService.addAnimal(requestMap);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return BovinoUtils.getResponseEntity(BovinoConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Page<AnimalWrapper>> getAllAnimals(Pageable pageable) {
        try{
            return animalService.getAllAnimals(pageable);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<Page<AnimalWrapper>>(Page.empty(), HttpStatus.INTERNAL_SERVER_ERROR);

    }


    @Override
    public ResponseEntity<AnimalWrapper> findById(String id) {
        try{
            return animalService.findAnimalById(id);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new AnimalWrapper(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateAnimal(Map<String, String> requestMap) {
        try{
            return animalService.updateAnimal(requestMap);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return BovinoUtils.getResponseEntity(BovinoConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> deleteAnimal(Map<String, String> requestMap) {
        try{
            return animalService.deleteAnimal(requestMap);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return BovinoUtils.getResponseEntity(BovinoConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public ResponseEntity<String> addNewWeight(Map<String, String> requestMap) {
        try{
            return animalService.addNewWeight(requestMap);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return BovinoUtils.getResponseEntity(BovinoConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);

    }




}
