package com.meubovinoapp.POJO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Timer;
import java.util.TimerTask;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OxPrice implements Serializable {


    @Serial
    private static final long serialVersionUID = 1L;

    private Animal ox;

    public static Double ibovespaData = 5.45;


}

