package com.meubovinoapp.restImpl;


import com.meubovinoapp.rest.DashboardRest;
import com.meubovinoapp.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class DashboardRestImpl implements DashboardRest {

    @Autowired
    DashboardService dashboardService;

    @Override
    public ResponseEntity<String> getCount(Integer userId_fk) {
      return dashboardService.getCount(userId_fk);
    }

    @Override
    public ResponseEntity<String> getAverageWeight(Integer userId_fk) {
        return dashboardService.getAverageWeight(userId_fk);
    }
}