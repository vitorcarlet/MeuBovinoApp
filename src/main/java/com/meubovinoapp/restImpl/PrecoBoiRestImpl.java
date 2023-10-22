package com.meubovinoapp.restImpl;

import com.meubovinoapp.constants.BovinoConstants;
import com.meubovinoapp.rest.PrecoBoiRest;
import com.meubovinoapp.service.PrecoBoiService;
import com.meubovinoapp.wrapper.AnimalWrapper;
import com.meubovinoapp.wrapper.PrecoBoiWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class PrecoBoiRestImpl implements PrecoBoiRest {

    @Autowired
    PrecoBoiService precoBoiService;

    @Override
    public ResponseEntity<String> countPrices() {
        try{
            return precoBoiService.countPrices();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(BovinoConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> getAveragePrice() {
        try{
            return precoBoiService.getAveragePrice();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(BovinoConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> addPrice(Map<String, String> requestMap) {
        try{
            return precoBoiService.addPrice(requestMap);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(BovinoConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @Override
    public ResponseEntity<List<PrecoBoiWrapper>> getAllPrices() {
        try{
            return precoBoiService.getAllPrices();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<List<PrecoBoiWrapper>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<PrecoBoiWrapper> setFirstResult() {
        try{
            return precoBoiService.setFirstResult();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<PrecoBoiWrapper>(new PrecoBoiWrapper(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
