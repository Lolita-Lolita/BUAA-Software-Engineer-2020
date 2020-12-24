package com.example.credit.entity;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "credit")
public class Credit {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "did")
    private Integer dishID;

    @Column(name = "look")
    private Double lookCredit;

    @Column(name = "smell")
    private Double smellCredit;

    @Column(name = "taste")
    private Double tasteCredit;

    @Column(name = "average")
    private Double averageCredit;
}
