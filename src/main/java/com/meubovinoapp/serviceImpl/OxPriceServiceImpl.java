package com.meubovinoapp.serviceImpl;

import com.meubovinoapp.JWT.JwtFilter;
import com.meubovinoapp.POJO.Animal;
import com.meubovinoapp.POJO.OxPrice;
import com.meubovinoapp.POJO.User;
import com.meubovinoapp.constants.BovinoConstants;
import com.meubovinoapp.dao.AnimalDAO;
import com.meubovinoapp.dao.UserDAO;
import com.meubovinoapp.service.OxPriceService;
import com.meubovinoapp.utils.BovinoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OxPriceServiceImpl implements OxPriceService {

    @Autowired
    UserDAO userDAO;

    @Autowired
    JwtFilter jwtFilter;

    @Autowired
    AnimalDAO animalDAO;
    @Override
    public ResponseEntity<String> getPriceById(Integer id) {
        try{
            //User userObj = userDAO.findByEmail(jwtFilter.getCurrentUser());
            Double oxPrice = OxPrice.ibovespaData;
            Animal animal = animalDAO.findById(id).get();
            String animalPrice = String.valueOf(animal.getActualWeight() * oxPrice);
            return new ResponseEntity<String>(animalPrice,HttpStatus.OK);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return BovinoUtils.getResponseEntity(BovinoConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
