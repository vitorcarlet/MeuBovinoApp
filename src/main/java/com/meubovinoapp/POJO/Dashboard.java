package com.meubovinoapp.POJO;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@DynamicInsert
@DynamicUpdate
@Table(name = "dashboard")

public class Dashboard implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToMany(mappedBy = "dashboard")
    private List<Animal> oxList;


    @Column(name = "averageMark")
    private float averageMark;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId_fk", nullable = false)
    private User ownerId;
}