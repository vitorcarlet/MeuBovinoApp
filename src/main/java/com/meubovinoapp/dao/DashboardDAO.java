package com.meubovinoapp.dao;

import com.meubovinoapp.POJO.Dashboard;
import com.meubovinoapp.wrapper.AnimalWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DashboardDAO extends JpaRepository<Dashboard,Integer> {


    @Query(name = "DashboardDAO.countAnimals")

    Float countAnimals(@Param("user_Id_fk") Integer user_Id_fk);

    @Query(name = "DashboardDAO.calculateAverageWeight")
    Double calculateAverageWeight(@Param("user_Id_fk") Integer user_Id_fk);

    List<AnimalWrapper> getAllAnimalsByOwnerId(@Param("ownerId")Integer ownerId);

}
