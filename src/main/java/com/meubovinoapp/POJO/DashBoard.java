package com.meubovinoapp.POJO;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@NamedQuery(name = "DashBoard.overallAverage", query = "select u from DashBoard u where u.email=:email")

@NamedQuery(name = "DashBoard.view history", query = "select new com.example.demo.wrapper.UserWrapper(u.id,u.name, u.email,u.contactNumber,u.status) from DashBoard u where u.role='user'")


@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "dashboard")
public class DashBoard implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "oxList")
    private List<Animal> animalList;

    @Column(name = "weight")
    private int weight;

    @OneToOne()
    @Column(name = "userId_fk", nullable = false)
    private User user;

}