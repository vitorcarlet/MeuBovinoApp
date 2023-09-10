package com.meubovinoapp.dao;

import com.meubovinoapp.POJO.Dashboard;
import com.meubovinoapp.POJO.Evolution;
import com.meubovinoapp.wrapper.EvolutionWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EvolutionDAO extends JpaRepository<Evolution,Integer> {

    //@Query("SELECT e FROM Evolution e WHERE e.animalId =: animalId")

    List<EvolutionWrapper> findAllDates(@Param("animalId") Integer animalId);

    Evolution findByAnimalId(@Param("animalId")Integer animalId);

}
