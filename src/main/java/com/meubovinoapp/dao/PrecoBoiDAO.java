package com.meubovinoapp.dao;

import com.meubovinoapp.POJO.PrecoBoi;
import com.meubovinoapp.wrapper.PrecoBoiWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.NamedQuery;
import java.util.List;

public interface PrecoBoiDAO extends JpaRepository<PrecoBoi, Long> {


    Long countPrices();

    PrecoBoi findTopByOrderByIdDesc();
    Double getAveragePrice();

    List<PrecoBoiWrapper> getAllPrices();

}