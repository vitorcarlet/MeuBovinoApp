package com.meubovinoapp.serviceImpl;

import com.meubovinoapp.JWT.JwtFilter;
import com.meubovinoapp.POJO.User;
import com.meubovinoapp.constants.BovinoConstants;
import com.meubovinoapp.dao.AnimalDAO;
import com.meubovinoapp.dao.DashboardDAO;
import com.meubovinoapp.dao.UserDAO;
import com.meubovinoapp.service.DashboardService;
import com.meubovinoapp.utils.BovinoUtils;
import com.meubovinoapp.wrapper.AnimalWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityManager;
import java.util.*;

@Service
@Slf4j
public class DashboardServiceImpl implements DashboardService {


    @Autowired
    DashboardDAO dashboardDAO;

    @Autowired
    UserDAO userDAO;

    @Autowired
    AnimalDAO animalDAO;

    @Autowired
    JwtFilter jwtFilter;

    @Autowired
    EntityManager entityManager;

    @Override
    public ResponseEntity<String> countAnimals(Integer user_Id_fk) {
        try {
            if (jwtFilter.isAdmin() || jwtFilter.isUser()) {
                String countDashboard = String.valueOf(dashboardDAO.countAnimals(user_Id_fk));
                if (!countDashboard.isBlank()) {
                    return new ResponseEntity<>(countDashboard, HttpStatus.OK);
                } else {
                    return BovinoUtils.getResponseEntity("we couldn't access the count of this user", HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                return BovinoUtils.getResponseEntity(BovinoConstants.UNAUTHORIZED_ACCESS, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return BovinoUtils.getResponseEntity(BovinoConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @Override
    public ResponseEntity<String> calculateAverageWeight(Integer user_Id_fk) {
        try {
            if (jwtFilter.isAdmin() || jwtFilter.isUser()){
                String averageWeight = String.valueOf(dashboardDAO.calculateAverageWeight(user_Id_fk));
                if (!averageWeight.isBlank()) {
                    return new ResponseEntity<>(averageWeight, HttpStatus.OK);
                } else {
                    return BovinoUtils.getResponseEntity("we couldn't access the average Weight of this user", HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                return BovinoUtils.getResponseEntity(BovinoConstants.UNAUTHORIZED_ACCESS, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return BovinoUtils.getResponseEntity(BovinoConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }



    @Override
    public ResponseEntity<Page<AnimalWrapper>> getAllAnimals(Pageable pageable) {

        User userObj = userDAO.findByEmail(jwtFilter.getCurrentUser());
        if (Objects.isNull(userObj)) {
            return new ResponseEntity<>(new PageImpl<>(new ArrayList<>()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        log.info(String.valueOf(userObj.getId()));
        //Pageable pageable = PageRequest.of(page, size);
        Page<AnimalWrapper> animals = animalDAO.getAllAnimalsByOwnerId2(userObj.getId().intValue(), pageable);
        return new ResponseEntity<>(animals, HttpStatus.OK);
    }

}
