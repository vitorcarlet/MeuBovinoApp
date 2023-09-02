package com.meubovinoapp.POJO;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@NamedQuery(name = "Animal.getAllOx", query = "select new com.example.demo.wrapper.UserWrapper(u.id,u.name, u.email,u.contactNumber,u.status) from Animal u where u.role='user'")
@NamedQuery(name = "Animal.findById", query = "select new com.example.demo.wrapper.UserWrapper(u.id,u.name, u.email,u.contactNumber,u.status) from Animal u where u.role='user'")



@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "animal")
public class Animal implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column (name = "race")
    private String race;

    @Column(name = "birth")
    private Date birth;

    @Column(name = "picture")
    private Byte[] picture;

    @Column(name = "actualWeight")
    private Integer actualWeight;

    @Column(name = "evolutionHistoric")
    private List<Evolution> evolutionHistoric;

    @OneToOne()
    @JoinColumn(name = "ownerId_fk", nullable = false)
    private User ownerId ;

}