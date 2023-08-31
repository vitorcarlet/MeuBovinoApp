package com.meubovinoapp.restImpl;

import com.meubovinoapp.constants.BovinoConstants;
import com.meubovinoapp.rest.UserRest;
import com.meubovinoapp.service.UserService;
import com.meubovinoapp.utils.BovinoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserRestImpl implements UserRest {

    @Autowired
    UserService userService;
    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        try{
            return userService.signUp(requestMap);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return BovinoUtils.getResponseEntity(BovinoConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
