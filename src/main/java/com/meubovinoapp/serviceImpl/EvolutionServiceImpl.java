package com.meubovinoapp.serviceImpl;

import com.meubovinoapp.JWT.JwtFilter;
import com.meubovinoapp.POJO.Animal;
import com.meubovinoapp.POJO.Evolution;
import com.meubovinoapp.POJO.User;
import com.meubovinoapp.constants.BovinoConstants;
import com.meubovinoapp.dao.AnimalDAO;
import com.meubovinoapp.dao.EvolutionDAO;
import com.meubovinoapp.dao.UserDAO;
import com.meubovinoapp.service.EvolutionService;
import com.meubovinoapp.utils.BovinoUtils;
import com.meubovinoapp.wrapper.EvolutionWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Slf4j

public class EvolutionServiceImpl implements EvolutionService {

    @Autowired
    JwtFilter jwtFilter;

    @Autowired
    AnimalDAO animalDAO;

    @Autowired
    EvolutionDAO evolutionDAO;

    @Autowired
    UserDAO userDAO;

//    @PersistenceContext
//    EntityManager entityManager;

    DateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

    DateFormat mysqlDateFormat = new SimpleDateFormat("yyyy-MM-dd");


    //@Transactional
    @Override
    public ResponseEntity<List<EvolutionWrapper>> getAllDates(String animalName) {
        try{
            if(jwtFilter.isAdmin() || jwtFilter.isUser()){
                User userObj = userDAO.findByEmail(jwtFilter.getCurrentUser());
                Animal animalObj = animalDAO.findAnimalByName(animalName,userObj.getId());
                if (Objects.isNull(animalObj)) {
                    return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
                }
                log.info(String.valueOf(animalObj.getId()));
                List<EvolutionWrapper> evolutionList = evolutionDAO.findAllDates(animalObj.getId());
                List<EvolutionWrapper> evolutionWrappers = new ArrayList<>();

                for(EvolutionWrapper evolution: evolutionList){
                    EvolutionWrapper evolutionWrapper = new EvolutionWrapper(evolution.getRegistryDate(),evolution.getWeight());
                    evolutionWrappers.add(evolutionWrapper);
                    log.info(String.valueOf(evolutionWrapper));
                }

                if (evolutionList.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
                return new ResponseEntity<>(evolutionWrappers,HttpStatus.OK);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<String> addEvolution(Animal animalId, int weight, Date date){
        try{
             evolutionDAO.save(new Evolution(animalId,weight,date));
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return BovinoUtils.getResponseEntity(BovinoConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Transactional
    public ResponseEntity<String> removeAllEvolutions(Integer id){
        try{
            // Primeiro, obtenha o objeto Animal com base no ID
            Optional<Animal> animalOptional = animalDAO.findById(id);

            if (animalOptional.isPresent()) {
                Animal animal = animalOptional.get();

                // Em seguida, remova todas as evoluções associadas a esse animal
                log.info("inside removeallEvolutions");
                evolutionDAO.deleteAllByAnimalId(animal);

                return BovinoUtils.getResponseEntity("All evolutions for the animal removed successfully.", HttpStatus.OK);
            } else {
                // Se o animal não for encontrado, retorne uma resposta adequada
                return BovinoUtils.getResponseEntity("Animal not found.", HttpStatus.NOT_FOUND);
            } }catch (Exception ex){
            ex.printStackTrace();
        }
        return BovinoUtils.getResponseEntity(BovinoConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public ResponseEntity<String> editEvolution(Map<String, String> requestMap) {
        try{
            Optional<Evolution> optional = evolutionDAO.findById(Integer.valueOf(requestMap.get("id")));
            if(!optional.isEmpty()){
                Evolution evolution = optional.get();
                String dataFormatada = formato.format(requestMap.get("date"));
                Date dataConvertida = mysqlDateFormat.parse(dataFormatada);
                evolution.setRegistryDate(dataConvertida);
                evolution.setWeight(Integer.parseInt(requestMap.get("weight")));

                evolutionDAO.save(evolution);
            }
            return BovinoUtils.getResponseEntity(BovinoConstants.INVALID_DATA,HttpStatus.INTERNAL_SERVER_ERROR);

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return BovinoUtils.getResponseEntity(BovinoConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private boolean validateUpdateAnimal(Map<String, String> requestMap) {
        if (
                requestMap.containsKey("id")
                        && requestMap.containsKey("registryDate")
                        && requestMap.containsKey("weight")
                        && requestMap.containsKey("animalId")
                        ) {
            return true;
        }
        return false;
    }

    @Override
    public ResponseEntity<String> removeEvolution(Integer id) {
        try{
            Optional<Evolution> evolutionOptional = evolutionDAO.findById(id);
            if (evolutionOptional.isPresent()) {
                Evolution evolution = evolutionOptional.get();
                evolutionDAO.delete(evolution);
                return BovinoUtils.getResponseEntity("Evolution removed successfully.", HttpStatus.OK);
            }
            return BovinoUtils.getResponseEntity("Evolution not found .", HttpStatus.BAD_REQUEST);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return BovinoUtils.getResponseEntity(BovinoConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
