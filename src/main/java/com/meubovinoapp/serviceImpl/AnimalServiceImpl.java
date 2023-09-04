package com.meubovinoapp.serviceImpl;

import com.meubovinoapp.JWT.CustomerUsersDetailsService;
import com.meubovinoapp.JWT.JwtFilter;
import com.meubovinoapp.JWT.JwtUtil;
import com.meubovinoapp.POJO.Animal;
import com.meubovinoapp.POJO.User;
import com.meubovinoapp.constants.BovinoConstants;
import com.meubovinoapp.dao.AnimalDAO;
import com.meubovinoapp.dao.UserDao;
import com.meubovinoapp.service.AnimalService;
import com.meubovinoapp.utils.BovinoUtils;
import com.meubovinoapp.wrapper.AnimalWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class AnimalServiceImpl implements AnimalService {

    @Autowired
    AnimalDAO animalDAO;


    @Autowired
    UserDao userDao;


    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    CustomerUsersDetailsService customerUsersDetailsService;

    @Autowired
    JwtUtil jwtUtil;


    @Autowired
    JwtFilter jwtFilter;

    public ResponseEntity<String> addAnimal(Map<String, String> requestMap) {
        log.info("Inside addAnimal {}", requestMap);
        try {
            log.info("Inside is User {}", requestMap);
            if (validateAddAnimal(requestMap)) {
                Animal animal = animalDAO.findAnimalByName(requestMap.get("name"));
                if (Objects.isNull(animal)) {
                    if (jwtFilter.isUser() || jwtFilter.isAdmin()) {
                        if (validateAddAnimal(requestMap)) {
                            animal = getAnimalFromMap(requestMap, false);
                            animalDAO.save(animal);
                            log.info("Inside Success  {}", requestMap);
                            return BovinoUtils.getResponseEntity("Successfully Registered.", HttpStatus.OK);
                        }
                    }else{
                        return BovinoUtils.getResponseEntity("Unauthorized access.", HttpStatus.UNAUTHORIZED);
                    }

                } else {
                    return BovinoUtils.getResponseEntity("This animal already exists.", HttpStatus.BAD_REQUEST);
                }
            } else {
                return BovinoUtils.getResponseEntity(BovinoConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return BovinoUtils.getResponseEntity(BovinoConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    public ResponseEntity<String> removeAnimal(Map<String, String> requestMap) {
        try {
            Animal animal = animalDAO.findAnimalByName(requestMap.get("name"));
            if (Objects.isNull(animal)) {
                return BovinoUtils.getResponseEntity("Animal not found.", HttpStatus.BAD_REQUEST);
            }
            animalDAO.delete(animal);
            return BovinoUtils.getResponseEntity("Animal removed successfully.", HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return BovinoUtils.getResponseEntity(BovinoConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> addNewWeight(Map<String, String> requestMap) {
        try {
            if (validatedNewWeight(requestMap)) {
                Animal animal = animalDAO.findAnimalById(Integer.valueOf(requestMap.get("id")));
                if (Objects.isNull(animal)) {
                    animal.setActualWeight(Integer.valueOf(requestMap.get("weight")));
                    animalDAO.save(animal);
                } else {
                    return BovinoUtils.getResponseEntity(BovinoConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return BovinoUtils.getResponseEntity(BovinoConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private boolean validatedNewWeight(Map<String, String> requestMap) {
        if (requestMap.containsKey("id") && requestMap.containsKey("weight")
        ) {
            return true;
        }
        return false;
    }

    private boolean validateAddAnimal(Map<String, String> requestMap) {
        if (requestMap.containsKey("name") && requestMap.containsKey("race")
                && requestMap.containsKey("birth")
                && requestMap.containsKey("actualWeight")
                && requestMap.containsKey(("ownerId"))) {
            return true;
        }
        return false;
    }

    private Animal getAnimalFromMap(Map<String, String> requestMap, boolean isAdd) {

        Animal animal = new Animal();

        User user = new User();
        user.setId(Integer.parseInt(requestMap.get("ownerId")));


        if (Objects.isNull(user)) {
            return animal;
        }
        animal.setName(requestMap.get("name"));
        animal.setRace(requestMap.get("race"));
        animal.setBirth(requestMap.get("birth"));
        animal.setActualWeight(Integer.valueOf(requestMap.get("actualWeight")));
        animal.setOwnerId(user);
        return animal;

    }


    public ResponseEntity<List<AnimalWrapper>> getAllAnimal() {
        try {
            return new ResponseEntity<>(animalDAO.getAllAnimal(), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    public ResponseEntity<String> updateAnimal(Map<String, String> requestMap) {
        try {

            Optional<Animal> optional = Optional.ofNullable(animalDAO.findAnimalById(Integer.parseInt(requestMap.get("id"))));
            if (!optional.isEmpty()) {
                if (validateAddAnimal(requestMap)) {
                    animalDAO.save(getAnimalFromMap(requestMap, false));
                    return BovinoUtils.getResponseEntity("Successfully Registered.", HttpStatus.OK);
                }
            } else {
                return BovinoUtils.getResponseEntity(BovinoConstants.UNAUTHORIZED_ACCESS, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return BovinoUtils.getResponseEntity(BovinoConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<Animal> findAnimalById(Integer id) {
        try {
            Animal animal = animalDAO.findById(id).get();
            if (Objects.isNull(animal)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            return new ResponseEntity<>(animalDAO.getById(id), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

    }

    @Override
    public ResponseEntity<List<AnimalWrapper>> getAllAnimals() {
        try {
            return (ResponseEntity<List<AnimalWrapper>>) animalDAO.getAllAnimal();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<List<AnimalWrapper>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
