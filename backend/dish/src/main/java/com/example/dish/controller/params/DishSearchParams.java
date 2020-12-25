package com.example.dish.controller.params;

import lombok.Data;

import java.util.List;

@Data
public class DishSearchParams {

    private Integer id;

    private String dishName;

    private List<List<Double>> dishPrice;

    private List<String> taste;

    private List<String> dishType;

    private List<String> location;
}
