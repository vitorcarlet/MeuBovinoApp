package com.meubovinoapp.POJO;

import com.meubovinoapp.wrapper.AnimalWrapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;


@NamedQuery(name = "Animal.getAllAnimal", query = "select new com.meubovinoapp.wrapper.AnimalWrapper(u.id,u.name, u.race,u.birth,u.actualWeight,u.ownerId) from Animal u where u.ownerId.id = :ownerId")
@NamedQuery(name = "Animal.getAllAnimalsByOwnerId", query = "select new com.meubovinoapp.wrapper.AnimalWrapper(u.id,u.name, u.race,u.birth,u.actualWeight,u.ownerId) from Animal u where u.ownerId.id = :ownerId")
@NamedQuery(name = "Animal.findById", query = "select new com.meubovinoapp.POJO.Animal(u.id,u.name, u.race,u.birth,u.actualWeight) from Animal u where u.id=:id")
@NamedQuery(name = "Animal.findAnimalByName", query = "select new com.meubovinoapp.POJO.Animal(u.id,u.name, u.race,u.birth,u.actualWeight) from Animal u where u.name=:name and u.ownerId.id = :ownerId")
@NamedQuery(name = "Animal.findAnimalById", query = "select new com.meubovinoapp.POJO.Animal(u.id,u.name, u.race,u.birth,u.actualWeight,u.ownerId) from Animal u where u.id=:id")



@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "animals")
@AllArgsConstructor
@NoArgsConstructor
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
    @Temporal(TemporalType.DATE)
    private Date birth;

//    @Column(name = "picture")
//    private byte[] picture;

    @Column(name = "actualWeight")
    private Integer actualWeight;

    @OneToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "evolutionHistoric")
    private Evolution evolutionHistoric;

    //private String role;

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "oxId_fk", nullable = false)
    private User ownerId;

    public void setBirth(Date birth) {
        this.birth = birth;

    }

    public Animal(Integer id, String name, String race, Date birth, Integer actualWeight) {
        this.id = id;
        this.name = name;
        this.race = race;
        this.birth = birth;
        this.actualWeight = actualWeight;
    }

    public Animal(Integer id, String name, String race, Date birth, Integer actualWeight, User user) {
        this.id = id;
        this.name = name;
        this.race = race;
        this.birth = birth;
        this.actualWeight = actualWeight;
        this.ownerId = user;
    }

    public Animal(String name,Integer actualWeight) {
        this.name = name;
        this.actualWeight = actualWeight;
    }

    public void setBirth(String birth) throws ParseException {
        this.birth = dateFormat.parse(birth);
    }

    @Transient
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
}
