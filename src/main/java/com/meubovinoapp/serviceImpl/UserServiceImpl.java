package com.meubovinoapp.serviceImpl;

import com.meubovinoapp.constants.BovinoConstants;
import com.meubovinoapp.service.UserService;
import com.meubovinoapp.utils.BovinoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        log.info("Inside signup {}", requestMap);
        if(validateSignUpMap(requestMap)){

        }else{
            return BovinoUtils.getResponseEntity(BovinoConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
        }
        return null;
    }

    private boolean validateSignUpMap(Map<String,String> requestMap){
        return requestMap.containsKey("name")
                && requestMap.containsKey("contactNumber")
                && requestMap.containsKey("email")
                && requestMap.containsKey("password");
    }
}
