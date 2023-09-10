package com.meubovinoapp.wrapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.meubovinoapp.POJO.Animal;
import com.meubovinoapp.POJO.Evolution;
import com.meubovinoapp.POJO.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EvolutionWrapper  {


    private Integer id;

    private Date registryDate;

    private int weight;

    private Animal animal;

    private Integer animalId;


    public EvolutionWrapper(Date registryDate, int weight, Animal animal) {
        this.registryDate = registryDate;
        this.weight = weight;
        this.animal = animal;
    }

    public EvolutionWrapper(Date registryDate, int weight, Integer animalId) {
        this.registryDate = registryDate;
        this.weight = weight;
        this.animalId = animalId;
    }

    public EvolutionWrapper(Date registryDate, int weight) {
        this.registryDate = registryDate;
        this.weight = weight;
    }
}
