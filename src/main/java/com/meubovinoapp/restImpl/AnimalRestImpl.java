package com.meubovinoapp.restImpl;

import com.meubovinoapp.POJO.Animal;
import com.meubovinoapp.constants.BovinoConstants;
import com.meubovinoapp.rest.AnimalRest;
import com.meubovinoapp.service.AnimalService;
import com.meubovinoapp.service.UserService;
import com.meubovinoapp.utils.BovinoUtils;
import com.meubovinoapp.wrapper.AnimalWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class AnimalRestImpl implements AnimalRest {

    @Autowired
    AnimalService animalService;

    @Override
    public ResponseEntity<String> addAnimal(Map<String, String> requestMap) {
        try{
            return animalService.addAnimal(requestMap);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return BovinoUtils.getResponseEntity(BovinoConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @Override
    public ResponseEntity<List<AnimalWrapper>> getAllAnimals() {
        try{
            return animalService.getAllAnimals();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<List<AnimalWrapper>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Animal> findById(Integer id) {
        try{
            return animalService.findAnimalById(id);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new Animal(),HttpStatus.INTERNAL_SERVER_ERROR);
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
            return animalService.deleteProduct(requestMap);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return BovinoUtils.getResponseEntity(BovinoConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public ResponseEntity<String> addNewWeight(Map<String, String> requestMap) {
        return null;
    }

    @Override
    public ResponseEntity<String> removeNewWeight(Map<String, String> requestMap) {
        return null;
    }


}
