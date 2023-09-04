package com.meubovinoapp.POJO;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;


@NamedQuery(name = "Animal.getAllAnimal", query = "select new com.meubovinoapp.wrapper.AnimalWrapper(u.id,u.name, u.race,u.birth,u.actualWeight) from Animal u where u.ownerId=:ownerId")
@NamedQuery(name = "Animal.findById", query = "select new com.meubovinoapp.wrapper.AnimalWrapper(u.id,u.name, u.race,u.birth,u.actualWeight) from Animal u where u.id=:id")
@NamedQuery(name = "Animal.findAnimalByName", query = "select new com.meubovinoapp.wrapper.AnimalWrapper(u.id,u.name, u.race,u.birth,u.actualWeight) from Animal u where u.name=:name")



@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "animals")
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
    private String birth;

//    @Column(name = "picture")
//    private byte[] picture;

    @Column(name = "actualWeight")
    private Integer actualWeight;

//    @Column(name = "evolutionHistoric")
//    private Evolution evolutionHistoric;

    //private String role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "oxId_fk", nullable = false)
    private User ownerId;

    public void setBirth(String birth) {
        this.birth = birth;
    }
}
