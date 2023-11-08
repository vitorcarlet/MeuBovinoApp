package com.meubovinoapp.serviceImpl;

import com.meubovinoapp.JWT.JwtFilter;
import com.meubovinoapp.POJO.Animal;
import com.meubovinoapp.POJO.PrecoBoi;
import com.meubovinoapp.POJO.User;
import com.meubovinoapp.constants.BovinoConstants;
import com.meubovinoapp.dao.PrecoBoiDAO;
import com.meubovinoapp.service.PrecoBoiService;
import com.meubovinoapp.utils.BovinoUtils;
import com.meubovinoapp.wrapper.PrecoBoiWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Slf4j
public class PrecoBoiServiceImpl implements PrecoBoiService {

    @Autowired
    PrecoBoiDAO precoBoiDAO;

    @Autowired
    JwtFilter jwtFilter;

    DateFormat mysqlDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public ResponseEntity<String> countPrices() {
        try {
            if (jwtFilter.isAdmin() || jwtFilter.isUser()) {
                String countPrices = String.valueOf(precoBoiDAO.countPrices());
                if (!countPrices.isBlank()) {
                    return new ResponseEntity<>(countPrices, HttpStatus.OK);
                } else {
                    return BovinoUtils.getResponseEntity("we couldn't access the count", HttpStatus.INTERNAL_SERVER_ERROR);
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
    public ResponseEntity<String> getAveragePrice() {
        try {
            if (jwtFilter.isAdmin() || jwtFilter.isUser()) {
                String averagePrice = String.valueOf(precoBoiDAO.getAveragePrice());
                if (!averagePrice.isBlank()) {
                    return new ResponseEntity<>(averagePrice, HttpStatus.OK);
                } else {
                    return BovinoUtils.getResponseEntity("we couldn't access the average", HttpStatus.INTERNAL_SERVER_ERROR);
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
    public ResponseEntity<PrecoBoiWrapper> setFirstResult() {
        try {
            if (jwtFilter.isAdmin() || jwtFilter.isUser()) {
               PrecoBoi precoBoi = precoBoiDAO.findTopByOrderByIdDesc();
                PrecoBoiWrapper precoBoiWrapper = new PrecoBoiWrapper(precoBoi.getDia(),precoBoi.getValor());

                return new ResponseEntity<PrecoBoiWrapper>(precoBoiWrapper,HttpStatus.OK);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<PrecoBoiWrapper>(new PrecoBoiWrapper(),HttpStatus.OK);
    }


    @Override
    public ResponseEntity<List<PrecoBoiWrapper>> getAllPrices() {
        try {
            if (jwtFilter.isAdmin() || jwtFilter.isUser()) {
                List<PrecoBoiWrapper> getAllPrices = precoBoiDAO.getAllPrices();


                return new ResponseEntity<>(getAllPrices,HttpStatus.OK);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> addPrice(Map<String,String> requestMap) {
        try{
            if (jwtFilter.isAdmin() || jwtFilter.isUser()) {
                PrecoBoi precoBoi = getPrecoFromMap(requestMap);

                precoBoiDAO.save(precoBoi);
                return BovinoUtils.getResponseEntity("Successfully Registered.", HttpStatus.OK);
            }else{
                return BovinoUtils.getResponseEntity(BovinoConstants.UNAUTHORIZED_ACCESS, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return BovinoUtils.getResponseEntity(BovinoConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }



    private PrecoBoi getPrecoFromMap(Map<String, String> requestMap) throws ParseException {

        PrecoBoi precoBoi = new PrecoBoi();

        precoBoi.setValor(Double.valueOf(requestMap.get("price")));
        String dataFormatada = requestMap.get("date");
        Date dataConvertida = mysqlDateFormat.parse(dataFormatada);
        precoBoi.setDia(dataConvertida);
        return precoBoi;

    }
}