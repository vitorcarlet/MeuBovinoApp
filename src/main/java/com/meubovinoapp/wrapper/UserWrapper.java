package com.meubovinoapp.wrapper;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserWrapper {

    //UserWrapper user = new UserWrapper(5,"abc","abc@gmail.com","564625465","false");

    private Integer id;

    private String name;

    private String email;

    private  String contactNumber;

    private  String status;


    public UserWrapper(Integer id, String name, String email, String contactNumber, String status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.contactNumber = contactNumber;
        this.status = status;
    }
}