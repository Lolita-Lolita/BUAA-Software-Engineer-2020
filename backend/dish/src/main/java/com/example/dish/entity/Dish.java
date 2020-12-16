package com.example.dish.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "dish")
public class Dish
{
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    //这里应该还有一个comment的实体，和Dish实体类保持一对一关系。
}
