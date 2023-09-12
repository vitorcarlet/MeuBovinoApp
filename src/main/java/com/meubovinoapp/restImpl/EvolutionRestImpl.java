package com.meubovinoapp.restImpl;

import com.meubovinoapp.POJO.Evolution;
import com.meubovinoapp.constants.BovinoConstants;
import com.meubovinoapp.rest.EvolutionRest;
import com.meubovinoapp.service.EvolutionService;
import com.meubovinoapp.utils.BovinoUtils;
import com.meubovinoapp.wrapper.EvolutionWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @Override
    public ResponseEntity<String> editEvolution(Map<String, String> requestMap) {
        try{
            return evolutionService.editEvolution(requestMap);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return BovinoUtils.getResponseEntity(BovinoConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> removeEvolution(Integer id) {
        try{
            return evolutionService.removeEvolution(id);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return BovinoUtils.getResponseEntity(BovinoConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
