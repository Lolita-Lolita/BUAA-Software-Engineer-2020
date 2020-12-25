package com.example.dish.controller.dto;

import lombok.Data;

@Data
public class DishDTO {

    private Integer id;

    private String dishName;

    private Double dishPrice;

    private String taste;

    private String dishType;

    private String location;
}
