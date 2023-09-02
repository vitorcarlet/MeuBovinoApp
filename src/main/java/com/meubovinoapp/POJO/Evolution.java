package com.meubovinoapp.POJO;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@NamedQuery(name = "Evolution.findByEmailId", query = "select u from Evolution u where u.email=:email")

@NamedQuery(name = "Evolution.getAllUser", query = "select new com.example.demo.wrapper.UserWrapper(u.id,u.name, u.email,u.contactNumber,u.status) from Evolution u where u.role='user'")

@NamedQuery(name = "Evolution.updateStatus", query = "update Evolution u set u.status=:status where u.id=:id")

@NamedQuery(name = "Evolution.getAllAdmin", query = "select u.email from Evolution u where u.role='admin'")

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "evolution")
public class Evolution implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "registryDate")
    private Date registry;

    @Column (name = "weight")
    private int weight;

    @OneToOne()
    @Column(name = "oxId_fk", nullable = false)
    private Long oxId;


}