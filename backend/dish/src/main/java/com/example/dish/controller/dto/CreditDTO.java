package com.example.dish.controller.dto;


import lombok.Data;

@Data
public class CreditDTO {

    private Integer id;

    private Integer dishID;

    private Double lookCredit;

    private Double smellCredit;

    private Double tasteCredit;

    private Double averageCredit;
}
