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

    private String registryDateString;

    private int weight;

    private Animal animalId;

    private Integer animalIdId;

    public EvolutionWrapper(Integer id, Date registryDate, int weight, Animal animalId, Integer animalIdId) {
        this.id = id;
        this.registryDate = registryDate;
        this.weight = weight;
        this.animalId = animalId;
        this.animalIdId = animalIdId;
    }

    public EvolutionWrapper(Integer id, Date registryDate, int weight, Animal animalId) {
        this.id = id;
        this.registryDate = registryDate;
        this.weight = weight;
        this.animalId = animalId;
    }

    public EvolutionWrapper(Date registryDate, int weight, Animal animalId) {
        this.registryDate = registryDate;
        this.weight = weight;
        this.animalId = animalId;
    }

    public EvolutionWrapper(Date registryDate, int weight, Integer animalIdId) {
        this.registryDate = registryDate;
        this.weight = weight;
        this.animalIdId = animalIdId;
    }

    public EvolutionWrapper(Date registryDate, int weight) {
        this.registryDate = registryDate;
        this.weight = weight;
    }

    public EvolutionWrapper(String dataFormatada, int weight) {
        this.registryDateString = dataFormatada;
        this.weight = weight;
    }
}
