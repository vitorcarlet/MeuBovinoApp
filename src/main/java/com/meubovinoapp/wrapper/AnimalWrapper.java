package com.meubovinoapp.wrapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.meubovinoapp.POJO.Animal;
import com.meubovinoapp.POJO.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class AnimalWrapper {


    private Integer id;

    private String name;

    private String race;

    private String birth;


    private int actualWeight;


    @JsonInclude(JsonInclude.Include.NON_NULL)
    private User ownerId;

    private Integer ownerIdId;


    public AnimalWrapper(Integer id, String name, String race, String birth, int actualWeight) {
        this.id = id;
        this.name = name;
        this.race = race;
        this.birth = birth;
        this.actualWeight = actualWeight;
    }

    public AnimalWrapper(Integer id, String name, String race, String birth, Integer actualWeight, User ownerId) {
        this.id = id;
        this.name = name;
        this.race = race;
        this.birth = birth;
        this.actualWeight = actualWeight;
        this.ownerId = ownerId;
    }

    public AnimalWrapper(Integer id, String name, String race, String birth, Integer actualWeight, Integer ownerId) {
        this.id = id;
        this.name = name;
        this.race = race;
        this.birth = birth;
        this.actualWeight = actualWeight;
        this.ownerIdId = ownerId;
    }

    public static AnimalWrapper fromAnimal(Animal animal) {
        AnimalWrapper animalWrapper = new AnimalWrapper();
        animalWrapper.setId(animal.getId());
        animalWrapper.setName(animal.getName());
        animalWrapper.setRace(animal.getRace());
        animalWrapper.setBirth(animal.getBirth());
        animalWrapper.setActualWeight(animal.getActualWeight());
        animalWrapper.setOwnerId(animal.getOwnerId());
        // Configure outros atributos conforme necessário
        return animalWrapper;
    }
}
