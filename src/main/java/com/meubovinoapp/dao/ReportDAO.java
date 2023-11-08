package com.meubovinoapp.dao;

import com.meubovinoapp.POJO.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportDAO extends JpaRepository<Report,Long> {
}
