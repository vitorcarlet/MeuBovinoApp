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

@NamedQuery(name = "DashboardDAO.countAnimals", query = "SELECT COUNT(a) FROM Animal a WHERE a.ownerId.id =:user_Id_fk")
@NamedQuery(name = "DashboardDAO.calculateAverageWeight", query = "SELECT AVG(a.actualWeight) FROM Animal a WHERE a.ownerId.id =:user_Id_fk")
@NamedQuery(name = "DashboardDAO.getAllAnimalsByOwnerId", query = "select new com.meubovinoapp.wrapper.AnimalWrapper(u.name,u.actualWeight) from Animal u where u.ownerId.id =:ownerId")
@NamedQuery(name = "Animal.getAllAnimalsByOwnerId2", query = "select new com.meubovinoapp.wrapper.AnimalWrapper(u.name,u.actualWeight) from Animal u where u.ownerId.id =:ownerId")







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