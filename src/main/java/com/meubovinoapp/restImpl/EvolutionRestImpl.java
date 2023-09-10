package com.meubovinoapp.restImpl;

import com.meubovinoapp.POJO.Evolution;
import com.meubovinoapp.rest.EvolutionRest;
import com.meubovinoapp.service.EvolutionService;
import com.meubovinoapp.wrapper.EvolutionWrapper;
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
    public ResponseEntity<List<EvolutionWrapper>> getAllDates(String animalName) {
        try{
            // try sem return não da warning
            return evolutionService.getAllDates(animalName);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<List<EvolutionWrapper>>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }
}
