package com.meubovinoapp.rest;

import com.meubovinoapp.POJO.Report;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/report")
public interface ReportRest {
    @PostMapping(path= "/generateReport")
    ResponseEntity<String> generateReport(@RequestBody Map<String,Object> requestMap);

    @GetMapping(path = "/getReports")
    ResponseEntity<List<Report>> getReports();

    @PostMapping(path = "/getPdf")
    ResponseEntity<byte[]> getPdf(@RequestBody Map<String,Object> requestMap);

    @PostMapping(path="/delete/{id}")
    ResponseEntity<String> deleteReport(@PathVariable Integer id);
}
