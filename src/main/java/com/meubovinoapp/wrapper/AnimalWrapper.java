package com.meubovinoapp.wrapper;

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


    private Integer ownerId;


    public AnimalWrapper(Integer id, String name, String race, String birth, int actualWeight) {
        this.id = id;
        this.name = name;
        this.race = race;
        this.birth = birth;
        this.actualWeight = actualWeight;
    }
}
