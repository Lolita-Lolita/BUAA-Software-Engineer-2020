package com.example.dish.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "dish")
public class Dish {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "dishname")
    private String dishName;

    @Column(name = "dishprice")
    private Double dishPrice;

    @Column(name = "dishtaste")
    private String taste;

    @Column(name = "dishtype")
    private String dishType;

    @Column(name = "dishlocation")
    private String location;

}
