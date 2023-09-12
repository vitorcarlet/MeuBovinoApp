package com.meubovinoapp.serviceImpl;

import com.meubovinoapp.JWT.CustomerUsersDetailsService;
import com.meubovinoapp.JWT.JwtFilter;
import com.meubovinoapp.JWT.JwtUtil;
import com.meubovinoapp.POJO.Animal;
import com.meubovinoapp.POJO.Evolution;
import com.meubovinoapp.POJO.User;
import com.meubovinoapp.constants.BovinoConstants;
import com.meubovinoapp.dao.AnimalDAO;
import com.meubovinoapp.dao.EvolutionDAO;
import com.meubovinoapp.dao.UserDAO;
import com.meubovinoapp.service.AnimalService;
import com.meubovinoapp.service.EvolutionService;
import com.meubovinoapp.utils.BovinoUtils;
import com.meubovinoapp.wrapper.AnimalWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Slf4j
public class AnimalServiceImpl implements AnimalService {

    @Autowired
    AnimalDAO animalDAO;


    @Autowired
    UserDAO userDao;


    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    CustomerUsersDetailsService customerUsersDetailsService;

    @Autowired
    JwtUtil jwtUtil;


    @Autowired
    JwtFilter jwtFilter;

    @Autowired
    EvolutionDAO evolutionDAO;


    @Autowired
    EvolutionService evolutionService;

    Date dataTempo;

    DateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

    DateFormat mysqlDateFormat = new SimpleDateFormat("yyyy-MM-dd");


    @Autowired
    private ConversionService conversionService;

    public ResponseEntity<String> addAnimal(Map<String, String> requestMap) {
        log.info("Inside addAnimal {}", requestMap);
        try {
            log.info("Inside is User {}", requestMap);
            if (validateAddAnimal(requestMap)) {
                Animal animal = animalDAO.findAnimalByName(requestMap.get("name"), Integer.valueOf(requestMap.get("ownerId")));
                if (Objects.isNull(animal)) {
                    if (jwtFilter.isUser() || jwtFilter.isAdmin()) {
                        animal = getAnimalFromMap(requestMap, false);


                        animalDAO.save(animal);

                        Evolution evolution = new Evolution();
                        evolution.setWeight(animal.getActualWeight());
                        evolution.setRegistryDate(animal.getBirth());
                        evolution.setAnimalId(animal);

                        evolutionDAO.save(evolution);


                        animal.setEvolutionHistoric(evolution);
                        animalDAO.save(animal);

                        log.info("Inside Success  {}", requestMap);
                        return BovinoUtils.getResponseEntity("Successfully Registered.", HttpStatus.OK);
                    } else {
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

    //fazer um delete da evolução do animal
    public ResponseEntity<String> deleteAnimal(Map<String, String> requestMap) {
        try {
            if (jwtFilter.isAdmin() || jwtFilter.isUser()) {
                User userObj = userDao.findByEmail(jwtFilter.getCurrentUser());
                Animal animal = animalDAO.findAnimalByName(requestMap.get("name"), userObj.getId());
                if (Objects.isNull(animal)) {
                    return BovinoUtils.getResponseEntity("Animal not found.", HttpStatus.BAD_REQUEST);
                }

                evolutionService.removeAllEvolutions(animal.getId());
                animalDAO.delete(animal);
                return BovinoUtils.getResponseEntity("Animal removed successfully.", HttpStatus.OK);
            }
            return BovinoUtils.getResponseEntity(BovinoConstants.INVALID_DATA, HttpStatus.INTERNAL_SERVER_ERROR);


        } catch (Exception ex) {
            ex.printStackTrace();
            return BovinoUtils.getResponseEntity(BovinoConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @Override
//    public ResponseEntity<String> deleteAnimal(Map<String, String> requestMap) {
//        try {
//            if (jwtFilter.isAdmin()) {
//                Integer id = Integer.valueOf(requestMap.get("id"));
//                Optional<Animal> optional = animalDAO.findById(id);
//                if (!optional.isEmpty()) {
//                    animalDAO.deleteById(id);
//                    evolutionService.removeAllEvolutions(id);
//                    return BovinoUtils.getResponseEntity("Animal Deleted Succesfully", HttpStatus.OK);
//                }
//                return BovinoUtils.getResponseEntity("Animal id does not exist.", HttpStatus.OK);
//            } else {
//                return BovinoUtils.getResponseEntity(BovinoConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return BovinoUtils.getResponseEntity(BovinoConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
//
//    }

    //Date é depreciado, usar LocalDate
    //Vou tentar tratar possivel erro de data na função addEvolution da classe EvolutionService
    public ResponseEntity<String> addNewWeight(Map<String, String> requestMap) {
        try {
            if (validatedNewWeight(requestMap)) {
                Animal animal = animalDAO.findAnimalById(Integer.valueOf(requestMap.get("id")));
                if (Objects.isNull(animal)) {
//                    Evolution evolution = evolutionDAO.findByAnimalIdAndWeight(animal.getActualWeight(),animal.getId());
//                    evolution.setWeight(Integer.valueOf(requestMap.get("weight")));
                    animal.setActualWeight(Integer.valueOf(requestMap.get("weight")));
                    String dataFormatada = formato.format(requestMap.get("date"));
                    Date dataConvertida = mysqlDateFormat.parse(requestMap.get(dataFormatada));
                    if (Objects.isNull(dataConvertida)) {
                        return BovinoUtils.getResponseEntity(BovinoConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
                    }
                    evolutionService.addEvolution(animal, animal.getActualWeight(), dataConvertida);
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


    //preciso resolver como o Evolution fica quando eu dou um update no animal
    //provavelmente vou alterar o ultimo evolution que tinha
    public ResponseEntity<String> updateAnimal(Map<String, String> requestMap) {
        try {
            if (jwtFilter.isAdmin() || jwtFilter.isUser()) {
                if (validateUpdateAnimal(requestMap)) {
                    int animalId = Integer.parseInt(requestMap.get("id"));
                    Optional<Animal> optional = Optional.ofNullable(animalDAO.findAnimalById(Integer.parseInt(requestMap.get("id"))));
                    if (!optional.isEmpty()) {
                        Animal existingAnimal = optional.get();
                        Evolution evolution = evolutionDAO.findByAnimalIdAndWeight(existingAnimal.getActualWeight(), existingAnimal.getId());
                        Animal updatedAnimal = getAnimalFromMap(requestMap, false);
                        updatedAnimal.setId(animalId);
                        evolution.setWeight(updatedAnimal.getActualWeight());
                        animalDAO.save(updatedAnimal);
                        evolutionDAO.save(evolution);
                        return BovinoUtils.getResponseEntity("Successfully Registered.", HttpStatus.OK);
                    } else {
                        return BovinoUtils.getResponseEntity(BovinoConstants.UNAUTHORIZED_ACCESS, HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                } else {
                    return BovinoUtils.getResponseEntity(BovinoConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
                }
            } else {
                return BovinoUtils.getResponseEntity(BovinoConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return BovinoUtils.getResponseEntity(BovinoConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    private boolean validateUpdateAnimal(Map<String, String> requestMap) {
        if (
                requestMap.containsKey("id")
                        && requestMap.containsKey("name")
                        && requestMap.containsKey("race")
                        && requestMap.containsKey("birth")
                        && requestMap.containsKey("actualWeight")
                        && requestMap.containsKey(("ownerId"))) {
            return true;
        }
        return false;
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

        User userObj = userDao.findByEmail(jwtFilter.getCurrentUser());
        if (Objects.isNull(userObj)) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        List<AnimalWrapper> animals = animalDAO.getAllAnimalsByOwnerId(userObj.getId());
        List<AnimalWrapper> animalWrappers = new ArrayList<>();
        for (AnimalWrapper animal : animals) {
            AnimalWrapper animalWrapper = new AnimalWrapper(animal.getId(), animal.getName(), animal.getRace(), animal.getBirth(), animal.getActualWeight(),
                    (animal.getOwnerId() != null) ? animal.getOwnerId().getId() : null
            );
            animalWrappers.add(animalWrapper);
        }
        return new ResponseEntity<>(animalWrappers, HttpStatus.OK);
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

    private Animal getAnimalFromMap(Map<String, String> requestMap, boolean isAdd) throws ParseException {

        Animal animal = new Animal();

        User user = new User();
        user.setId(Integer.parseInt(requestMap.get("ownerId")));


        if (Objects.isNull(user)) {
            return animal;
        }
        animal.setName(requestMap.get("name"));
        animal.setRace(requestMap.get("race"));
        String dataFormatada = requestMap.get("birth");
        Date dataConvertida = mysqlDateFormat.parse(dataFormatada);
        animal.setBirth(dataConvertida);
        animal.setActualWeight(Integer.valueOf(requestMap.get("actualWeight")));
        animal.setOwnerId(user);
        return animal;

    }


}
