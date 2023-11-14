package com.meubovinoapp.restImpl;

import com.meubovinoapp.POJO.MonthlyData;
import com.meubovinoapp.constants.BovinoConstants;
import com.meubovinoapp.rest.MonthlyRest;
import com.meubovinoapp.service.MonthlyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MonthlyRestImpl implements MonthlyRest {

    @Autowired
    MonthlyService monthlyService;

    @Override
    public ResponseEntity<List<MonthlyData>> getMonthlyWeight() {
        try{
            return monthlyService.getMonthlyWeight();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<List<MonthlyData>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
