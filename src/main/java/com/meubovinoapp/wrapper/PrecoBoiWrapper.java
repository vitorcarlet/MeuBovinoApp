package com.meubovinoapp.wrapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.meubovinoapp.POJO.PrecoBoi;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PrecoBoiWrapper {

    private Long id;
    private Double valor;
    private Date dia;

    public PrecoBoiWrapper(Long id, Double valor, Date dia) {
        this.id = id;
        this.valor = valor;
        this.dia = dia;
    }

    public PrecoBoiWrapper(Long id) {
        this.id = id;
    }

    public PrecoBoiWrapper(Long id, Double valor) {
        this.id = id;
        this.valor = valor;
    }

    public PrecoBoiWrapper(Date dia, Double valor ) {
        this.dia = dia;
        this.valor = valor;
    }
}
