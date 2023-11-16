package com.meubovinoapp.dao;

import com.meubovinoapp.POJO.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReportDAO extends JpaRepository<Report,Integer> {

    List<Report> getAllReports();

    List<Report> getReportByUserName(@Param("username")String currentUser);
}
