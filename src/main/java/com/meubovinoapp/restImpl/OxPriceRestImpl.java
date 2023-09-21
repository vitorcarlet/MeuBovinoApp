package com.meubovinoapp.restImpl;

import com.meubovinoapp.constants.BovinoConstants;
import com.meubovinoapp.rest.OxPriceRest;
import com.meubovinoapp.service.OxPriceService;
import com.meubovinoapp.utils.BovinoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OxPriceRestImpl implements OxPriceRest {


    @Autowired
    OxPriceService oxPriceService;

    @Override
    public ResponseEntity<String> getPriceById(Integer id) {
        try{
           return oxPriceService.getPriceById(id);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return BovinoUtils.getResponseEntity(BovinoConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
