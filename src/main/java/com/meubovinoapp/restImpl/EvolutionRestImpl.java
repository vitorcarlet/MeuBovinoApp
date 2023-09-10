package com.meubovinoapp.restImpl;

import com.meubovinoapp.POJO.Evolution;
import com.meubovinoapp.rest.EvolutionRest;
import com.meubovinoapp.service.EvolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EvolutionRestImpl implements EvolutionRest {

    @Autowired
    EvolutionService evolutionService;


    @Override
    public ResponseEntity<List<Evolution>> getAllDates(Integer animalId) {
        try{
            evolutionService.getAllDates(animalId);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<List<Evolution>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
