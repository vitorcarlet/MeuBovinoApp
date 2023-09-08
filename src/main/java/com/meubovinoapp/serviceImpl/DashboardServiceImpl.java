package com.meubovinoapp.serviceImpl;

import com.meubovinoapp.POJO.Dashboard;
import com.meubovinoapp.constants.BovinoConstants;
import com.meubovinoapp.dao.DashboardDAO;
import com.meubovinoapp.service.DashboardService;
import com.meubovinoapp.utils.BovinoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class DashboardServiceImpl implements DashboardService {


    @Autowired
    DashboardDAO dashboardDAO;

    @Override
    public ResponseEntity<String> getCount(Integer userId_fk) {
        try{
            Float countDashboard = dashboardDAO.getCount(userId_fk);

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return BovinoUtils.getResponseEntity(BovinoConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> getAverageWeight(Integer userId_fk) {
        try{

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return BovinoUtils.getResponseEntity(BovinoConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
