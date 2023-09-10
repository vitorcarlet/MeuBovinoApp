package com.meubovinoapp.serviceImpl;

import com.meubovinoapp.JWT.JwtFilter;
import com.meubovinoapp.POJO.Animal;
import com.meubovinoapp.POJO.Evolution;
import com.meubovinoapp.constants.BovinoConstants;
import com.meubovinoapp.dao.AnimalDAO;
import com.meubovinoapp.dao.EvolutionDAO;
import com.meubovinoapp.service.EvolutionService;
import com.meubovinoapp.utils.BovinoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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


    @Override
    public ResponseEntity<List<Evolution>> getAllDates(Integer animalId) {
        try{
            if(jwtFilter.isAdmin() || jwtFilter.isUser()){
                Animal animalObj = animalDAO.findAnimalById(animalId);
                if (Objects.isNull(animalObj)) {
                    return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
                }
                log.info(String.valueOf(animalObj.getId()));
                List<Evolution> evolutionList = evolutionDAO.findAllDates(animalId);
                return new ResponseEntity<>(evolutionList,HttpStatus.OK);
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
