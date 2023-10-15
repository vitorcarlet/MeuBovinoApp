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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
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

    @Autowired
    EntityManager entityManager;

    Date dataTempo;

    DateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

    DateFormat mysqlDateFormat = new SimpleDateFormat("yyyy-MM-dd");


    @Autowired
    private ConversionService conversionService;

    @Transactional
    public ResponseEntity<String> addAnimal(Map<String, String> requestMap) {
        log.info("Inside addAnimal {}", requestMap);
        try {
            log.info("Inside is User {}", requestMap);
            if (jwtFilter.isAdminOrUser()) {
                if (validateAddAnimal(requestMap)) {
                    Animal animal = animalDAO.findAnimalByName(requestMap.get("name"), Integer.valueOf(requestMap.get("ownerId")));
                    if (Objects.isNull(animal)) {
                        if (jwtFilter.isUser() || jwtFilter.isAdmin()) {
                            animal = getAnimalFromMap(requestMap);


                            animalDAO.save(animal);

                            Evolution evolution = new Evolution();
                            evolution.setWeight(animal.getActualWeight());
                            evolution.setRegistryDate(animal.getBirth());
                            evolution.setAnimalId(animal);

                            evolutionDAO.save(evolution);


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
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return BovinoUtils.getResponseEntity(BovinoConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

    }


    //fazer um delete da evolução do animal
    public ResponseEntity<String> deleteAnimal(Map<String, String> requestMap) {
        try {
            if (jwtFilter.isAdminOrUser()) {
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


    @Override
    public ResponseEntity<Page<AnimalWrapper>> getAllAnimals(Pageable pageable, String name) {
        try {
            if (jwtFilter.isAdminOrUser()) {
                User userObj = userDao.findByEmail(jwtFilter.getCurrentUser());
                if (Objects.isNull(userObj)) {
                    return new ResponseEntity<>(Page.empty(), HttpStatus.INTERNAL_SERVER_ERROR);
                }

                Page<AnimalWrapper> animals;
                if (name != null) {
                    String nameParameter = "%"+name+"%";
                    animals = animalDAO.getAllAnimalsByOwnerIdAndNameLike(userObj.getId(), nameParameter, pageable);
                } else {
                    animals = animalDAO.getAllAnimalsByOwnerId(userObj.getId(), pageable);
                }

                return new ResponseEntity<>(animals, HttpStatus.OK);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return new ResponseEntity<>(Page.empty(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<AnimalWrapper> findAnimalById(String id) {
        try {
            Integer idInt = Integer.valueOf(id);
            Animal animal = animalDAO.findById(idInt).get();
            if (Objects.isNull(animal)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            AnimalWrapper animalWrapper = new AnimalWrapper(animal.getId(), animal.getName(), animal.getRace(),animal.getActualWeight(), animal.getOwnerId(), animal.getBirth(), animal.getDataInsercao().getTime());

            return new ResponseEntity<>(animalWrapper, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

    }


    //Date é depreciado, usar LocalDate
    //Vou tentar tratar possivel erro de data na função addEvolution da classe EvolutionService
    @Transactional
    public ResponseEntity<String> addNewWeight(Map<String, String> requestMap) {
        try {
            if (jwtFilter.isAdminOrUser()) {
                if (validatedNewWeight(requestMap)) {
                    Animal animal = animalDAO.findAnimalById(Integer.valueOf(requestMap.get("id")));
                    if (!Objects.isNull(animal)) {
//                    Evolution evolution = evolutionDAO.findByAnimalIdAndWeight(animal.getActualWeight(),animal.getId());
//                    evolution.setWeight(Integer.valueOf(requestMap.get("weight")));
                        animal = entityManager.merge(animal);
                        animal.setActualWeight(Integer.valueOf(requestMap.get("weight")));
                        String dataFormatada = requestMap.get("date");
                        Date dataConvertida = mysqlDateFormat.parse(dataFormatada);
                        if (Objects.isNull(dataConvertida)) {
                            return BovinoUtils.getResponseEntity(BovinoConstants.INVALID_DATA + "erro da data", HttpStatus.BAD_REQUEST);
                        }
                        evolutionService.addEvolution(animal, animal.getActualWeight(), dataConvertida);

                        animalDAO.save(animal);
                        return BovinoUtils.getResponseEntity("novo peso adicionado com sucesso", HttpStatus.OK);

                    } else {
                        return BovinoUtils.getResponseEntity(BovinoConstants.INVALID_DATA + "erro do animal", HttpStatus.BAD_REQUEST);
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return BovinoUtils.getResponseEntity(BovinoConstants.SOMETHING_WENT_WRONG + "wrong do Animaladdevolution", HttpStatus.INTERNAL_SERVER_ERROR);
    }


    //preciso resolver como o Evolution fica quando eu dou um update no animal
    //provavelmente vou alterar o ultimo evolution que tinha

    @Transactional
    public ResponseEntity<String> updateAnimal(Map<String, String> requestMap) {
        try {
            if (jwtFilter.isAdminOrUser()) {
                if (validateUpdateAnimal(requestMap)) {
                    int animalId = Integer.parseInt(requestMap.get("id"));
                    Optional<Animal> optional = Optional.ofNullable(animalDAO.findAnimalById(Integer.parseInt(requestMap.get("id"))));
                    if (!optional.isEmpty()) {

                        Animal existingAnimal = optional.get();
                        Evolution evolution = evolutionDAO.findByAnimalIdAndWeight(existingAnimal.getActualWeight(), existingAnimal.getId());
                        if (!Objects.isNull(evolution)) {
                            log.info("inside update Animal");
                            Animal updatedAnimal = getAnimalFromMap(requestMap);
                            updatedAnimal.setId(animalId);

                            evolution.setWeight(updatedAnimal.getActualWeight());

                            animalDAO.save(updatedAnimal);
                            evolutionDAO.save(evolution);
                            return BovinoUtils.getResponseEntity("Successfully Registered.", HttpStatus.OK);
                        }
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







    private boolean validatedNewWeight(Map<String, String> requestMap) {
        if (requestMap.containsKey("id") && requestMap.containsKey("weight") && requestMap.containsKey("date")
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

    private Animal getAnimalFromMap(Map<String, String> requestMap) throws ParseException {

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

