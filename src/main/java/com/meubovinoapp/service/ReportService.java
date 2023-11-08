package com.meubovinoapp.service;

import com.meubovinoapp.POJO.Report;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface ReportService {
    ResponseEntity<String> generateReport(Map<String, Object> requestMap);

    ResponseEntity<List<Report>> getReport();

    ResponseEntity<byte[]> getPdf(Map<String, Object> requestMap);

    ResponseEntity<String> deleteReport(Integer id);
}
