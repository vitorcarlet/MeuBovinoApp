package com.meubovinoapp.POJO;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@DynamicInsert
@DynamicUpdate

@Table(name = "precos")

@NamedQuery(name = "PrecoBoi.countPrices", query = "SELECT COUNT(p) FROM PrecoBoi p")
@NamedQuery(name = "PrecoBoi.getAveragePrice", query = "SELECT AVG(p.valor) FROM PrecoBoi p")
@NamedQuery(name = "PrecoBoi.getAllPrices", query = "select new com.meubovinoapp.wrapper.PrecoBoiWrapper(p.dia, p.valor) from PrecoBoi p")
//@NamedQuery(name = "PrecoBoi.setFirstResult", query = "SELECT new com.meubovinoapp.wrapper.PrecoBoiWrapper(p.dia, p.valor) FROM PrecoBoi p ORDER BY p.id DESC  ")

//com/meubovinoapp/wrapper/PrecoBoiWrapper.java
public class PrecoBoi implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "valor", nullable = false)
    private Double valor;

    @Column(name = "dia", nullable = false)
    private Date dia;

}