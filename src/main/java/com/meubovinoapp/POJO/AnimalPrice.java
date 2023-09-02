package com.meubovinoapp.POJO;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@NamedQuery(name = "AnimalPrice.findByEmailId", query = "select u from AnimalPrice u where u.email=:email")

@NamedQuery(name = "AnimalPrice.getAllUser", query = "select new com.example.demo.wrapper.UserWrapper(u.id,u.name, u.email,u.contactNumber,u.status) from AnimalPrice u where u.role='user'")

@NamedQuery(name = "AnimalPrice.updateStatus", query = "update AnimalPrice u set u.status=:status where u.id=:id")

@NamedQuery(name = "AnimalPrice.getAllAdmin", query = "select u.email from AnimalPrice u where u.role='admin'")

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "price")
public class AnimalPrice implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "registryDate")
    private Date registry;

    @Column(name = "weight")
    private int weight;

    @OneToOne()
    @Column(name = "oxId_fk", nullable = false)
    private Animal animal;

}
