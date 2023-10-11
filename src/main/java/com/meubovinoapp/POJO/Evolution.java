package com.meubovinoapp.POJO;

import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;


@NamedQuery(name = "Evolution.findAllDates", query = "SELECT new com.meubovinoapp.wrapper.EvolutionWrapper(e.id,e.registryDate, e.weight, e.animalId)  FROM Evolution e WHERE e.animalId.id =:animalId")

@NamedQuery(name = "Evolution.findByAnimalId", query = "SELECT e.id, e.registryDate, e.weight FROM Evolution e WHERE e.animalId.id =:animalId")

//@NamedQuery(name = "Evolution.findByAnimalIdAndWeight", query = "SELECT e.id, e.registryDate, e.weight from Evolution e Where e.weight =: actualWeight  and e.animalId.id=:animalId ")

@NamedQuery(name = "Evolution.findByAnimalIdAndWeight", query = "SELECT e FROM Evolution e JOIN com.meubovinoapp.POJO.Animal a ON e.animalId = a.id WHERE e.weight = :actualWeight AND a.id = :animalId")

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "evolution")
@AllArgsConstructor
@NoArgsConstructor

public class Evolution implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "registryDate")
    @Temporal(TemporalType.DATE)
    private Date registryDate;

    @Column(name = "weight")
    private int weight;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "animalId_fk", nullable = false)
    private Animal animalId;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dtcreated")
    private Date dataInsercao;

    public Evolution(Animal animalId, int weight, Date date) {
        this.animalId = animalId;
        this.weight = weight;
        this.registryDate = date;
    }
}
