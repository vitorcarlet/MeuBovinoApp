package com.meubovinoapp.POJO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ox_Price implements Serializable {


    @Serial
    private static final long serialVersionUID = 1L;

    private Animal ox;

    private Double ibovespaData = 5.45;

}
