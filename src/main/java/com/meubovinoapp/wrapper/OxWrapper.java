package com.meubovinoapp.wrapper;

import com.meubovinoapp.POJO.Evolution;
import com.meubovinoapp.POJO.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class OxWrapper {

    //UserWrapper user = new UserWrapper(5,"abc","abc@gmail.com","564625465","false");

    private Integer id;

    private String name;

    private String race;

    private Date birth;

    private Byte[] picture;

    private int actualWeight;

    private List<Evolution> evolutionHistoric;

    private User ownerId;


    public OxWrapper(Integer id, String name, String race, Date birth, Byte[] picture, int actualWeight, List<Evolution> evolutionHistoric, User ownerId) {
        this.id = id;
        this.name = name;
        this.race = race;
        this.birth = birth;
        this.picture = picture;
        this.actualWeight = actualWeight;
        this.evolutionHistoric = evolutionHistoric;
        this.ownerId = ownerId;
    }
}
