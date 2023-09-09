package com.meubovinoapp.restImpl;


import com.meubovinoapp.rest.DashboardRest;
import com.meubovinoapp.service.DashboardService;
import com.meubovinoapp.wrapper.AnimalWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DashboardRestImpl implements DashboardRest {

    @Autowired
    DashboardService dashboardService;

    @Override
    public ResponseEntity<String> countAnimals(Integer user_Id_fk) {
      return dashboardService.countAnimals(user_Id_fk);
    }

    @Override
    public ResponseEntity<String> calculateAverageWeight(Integer user_Id_fk) {
        return dashboardService.calculateAverageWeight(user_Id_fk);
    }

    @Override
    public ResponseEntity<List<AnimalWrapper>> getAllAnimals() {
        try{
            return dashboardService.getAllAnimals();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<List<AnimalWrapper>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}