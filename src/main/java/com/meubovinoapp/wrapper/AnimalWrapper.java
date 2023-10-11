package com.meubovinoapp.wrapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.meubovinoapp.POJO.Animal;
import com.meubovinoapp.POJO.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnimalWrapper {


    private Integer id;

    private String name;

    private String race;

    private Date birth;

    private String birthday;


    private int actualWeight;

    private Long dataInsercao;


    @JsonInclude(JsonInclude.Include.NON_NULL)
    private User ownerId;

    private Integer ownerIdId;


    public AnimalWrapper(Integer id, String name, String race, Date birth, int actualWeight) {
        this.id = id;
        this.name = name;
        this.race = race;
        this.birth = birth;
        this.actualWeight = actualWeight;
    }

    public AnimalWrapper(Integer id, String name, String race, Date birth, Integer actualWeight, User ownerId) {
        this.id = id;
        this.name = name;
        this.race = race;
        this.birth = birth;
        this.actualWeight = actualWeight;
        this.ownerId = ownerId;
    }

    public AnimalWrapper(Integer id, String name, String race, Date birth, Integer actualWeight, Integer ownerId) {
        this.id = id;
        this.name = name;
        this.race = race;
        this.birth = birth;
        this.actualWeight = actualWeight;
        this.ownerIdId = ownerId;
    }

    public AnimalWrapper(String name, int actualWeight) {
        this.name = name;
        this.actualWeight = actualWeight;
    }

    public AnimalWrapper(Integer id, String name, String race, String format, int actualWeight, Integer integer) {
        this.id = id;
        this.name = name;
        this.race = race;
        this.birthday = format;
        this.actualWeight = actualWeight;
        this.ownerIdId = integer;
    }

    public AnimalWrapper(Integer id, String name, String race, int actualWeight, User ownerId, Date birth, Long dataInsercao) {
        this.id = id;
        this.name = name;
        this.race = race;
        this.birthday = String.valueOf(birth);
        this.actualWeight = actualWeight;
        this.ownerIdId = ownerId.getId();
        this.dataInsercao = dataInsercao;
    }

    public static AnimalWrapper fromAnimal(Animal animal) {
        AnimalWrapper animalWrapper = new AnimalWrapper();
        animalWrapper.setId(animal.getId());
        animalWrapper.setName(animal.getName());
        animalWrapper.setRace(animal.getRace());
        animalWrapper.setBirth(animal.getBirth());
        animalWrapper.setActualWeight(animal.getActualWeight());
        animalWrapper.setOwnerId(animal.getOwnerId());
        animalWrapper.setDataInsercao(animal.getDataInsercao().getTime());
        // Configure outros atributos conforme necessário
        return animalWrapper;
    }
}
