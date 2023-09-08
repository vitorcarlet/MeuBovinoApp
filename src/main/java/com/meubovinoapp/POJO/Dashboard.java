package com.meubovinoapp.POJO;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@DynamicInsert
@DynamicUpdate
@Table(name = "dashboard")

@NamedQuery(name = "DashboardDAO.getCount", query = "SELECT COUNT(a) FROM Animal a WHERE a.ox_id_fk = :userId_fk")
@NamedQuery(name = "DashboardDAO.getAverageWeight", query = "SELECT AVG(a.actual_weight) FROM Animal a WHERE a.ox_id_fk = :userId_fk")


public class Dashboard implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId_fk", nullable = false)
    private User ownerId;
}