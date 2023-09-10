package com.meubovinoapp.POJO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;


@NamedQuery(name = "Evolution.findAllDates", query = "SELECT new com.meubovinoapp.wrapper.EvolutionWrapper(e.registryDate, e.weight, e.animalId)  FROM Evolution e WHERE e.animalId.id =:animalId")

@NamedQuery(name = "Evolution.findByAnimalId", query = "SELECT e.id, e.registryDate, e.weight FROM Evolution e WHERE e.animalId.id =:animalId")


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
    private Date registryDate;

    @Column(name = "weight")
    private int weight;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "animalId_fk", nullable = false)
    private Animal animalId;

    public Evolution(Animal animalId, int weight, Date date) {
        this.animalId = animalId;
        this.weight = weight;
        this.registryDate = date;
    }
}
