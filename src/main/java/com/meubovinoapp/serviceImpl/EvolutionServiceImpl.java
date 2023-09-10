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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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

    public ResponseEntity<String> removeAllEvolutions(Integer id){
        try{
            evolutionDAO.delete(evolutionDAO.findByAnimalId(id));
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return BovinoUtils.getResponseEntity(BovinoConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
