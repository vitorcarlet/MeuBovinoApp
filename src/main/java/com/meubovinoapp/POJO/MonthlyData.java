package com.meubovinoapp.POJO;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data

@Table(name = "recentWeights")

public class MonthlyData {

    @Id
    @Column(name = "month")
    private String month;

    @Column(name = "average_weight")
    private int averageWeight;

    // getters e setters
}
