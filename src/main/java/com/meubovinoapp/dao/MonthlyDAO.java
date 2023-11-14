package com.meubovinoapp.dao;

import com.meubovinoapp.POJO.MonthlyData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface MonthlyDAO extends JpaRepository<MonthlyData, String> {
    @Query(nativeQuery = true, value =
            "SELECT m.mes, COALESCE(ROUND(AVG(e.weight), 0), 0) AS mediaPeso " +
                    "FROM meses_recentes m " +
                    "LEFT JOIN evolution e ON DATE_FORMAT(e.registry_date, '%Y-%m') = m.mes " +
                    "GROUP BY m.mes " +
                    "ORDER BY m.mes DESC")
    List<Object[]> getMonthlyData();
}
