package com.meubovinoapp.serviceImpl;

import com.meubovinoapp.JWT.JwtFilter;
import com.meubovinoapp.POJO.MonthlyData;
import com.meubovinoapp.POJO.User;
import com.meubovinoapp.dao.UserDAO;
import com.meubovinoapp.service.MonthlyService;
import com.meubovinoapp.service.PrecoBoiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class MonthlyServiceImpl implements MonthlyService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    UserDAO userDAO;

    @Autowired
    JwtFilter jwtFilter;
    @Override
    @Transactional
    public ResponseEntity<List<MonthlyData>> getMonthlyWeight() {

        User userObj = userDAO.findByEmail(jwtFilter.getCurrentUser());

        // Criação da tabela temporária para o usuário
        String createTableQuery = "CREATE TEMPORARY TABLE IF NOT EXISTS user_temp_table_" + userObj.getId() +
                " AS SELECT DATE_FORMAT(CURDATE() - INTERVAL seq MONTH, '%Y-%m') AS mes " +
                " FROM (SELECT 0 AS seq UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION " +
                " SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 " +
                " UNION SELECT 9 UNION SELECT 10 UNION SELECT 11 UNION SELECT 12) t;";
        entityManager.createNativeQuery(createTableQuery).executeUpdate();

        // Consulta principal
        String selectQuery = "SELECT m.mes, COALESCE(ROUND(AVG(e.weight), 0), 0) AS mediaPeso " +
                "FROM user_temp_table_" + userObj.getId()  + " m " +
                "LEFT JOIN evolution e ON DATE_FORMAT(e.registry_date, '%Y-%m') = m.mes " +
                "LEFT JOIN animals a ON e.animal_id_fk = a.id " +
                "WHERE a.ox_id_fk = :userId " + // Adicionando um espaço antes da cláusula WHERE
                "GROUP BY m.mes " +
                "ORDER BY m.mes DESC";

        List<Object[]> result = entityManager.createNativeQuery(selectQuery)
                .setParameter("userId", userObj.getId())
                .getResultList();

        // Mapeamento dos resultados para a classe MonthlyData
        List<MonthlyData> monthlyDataList = new ArrayList<>();
        for (Object[] row : result) {
            MonthlyData monthlyData = new MonthlyData();
            monthlyData.setMonth((String) row[0]);
            monthlyData.setAverageWeight(((BigDecimal) row[1]).intValue());
            monthlyDataList.add(monthlyData);
        }

        // Exclusão da tabela temporária do usuário
        String dropTableQuery = "DROP TEMPORARY TABLE IF EXISTS user_temp_table_" + userObj.getId() ;
        entityManager.createNativeQuery(dropTableQuery).executeUpdate();

        return new ResponseEntity<List<MonthlyData>>(monthlyDataList, HttpStatus.OK);
    }
}
