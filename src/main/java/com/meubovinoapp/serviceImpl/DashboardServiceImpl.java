package com.meubovinoapp.serviceImpl;

import com.meubovinoapp.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DashboardServiceImpl implements DashboardService {


    @Override
    public ResponseEntity<Map<String, Object>> getCount() {
        return null;
    }
}
