package com.meubovinoapp.dao;

import com.meubovinoapp.POJO.Dashboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface DashboardDAO extends JpaRepository<Dashboard,Integer> {


    Float getCount(@Param("userId_fk") Integer userId_fk);

    Float getAverageWeight(@Param("userId_fk") Integer userId_fk);

}
