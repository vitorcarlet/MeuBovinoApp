package com.meubovinoapp.POJO;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "report")

@NamedQuery(name = "Report.getAllReports", query = "select r from Report r order by r.id desc")
@NamedQuery(name = "Report.getReportByUserName", query = "select r from Report r where r.createdBy=:username order by r.id desc")

public class Report implements Serializable {

    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "paymentmethod")
    private String paymentMethod;

    @Column(name = "total")
    private Integer total;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dtcreated")
    private Date dataInsercao;

    @Column(name = "createdby")
    private String createdBy;
}
