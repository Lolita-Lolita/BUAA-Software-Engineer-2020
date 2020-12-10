package com.example.qialame.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dish")
public class DishController
{
    @RequestMapping("/test")
    public String Test(){
        System.out.println("Software 2020！");
        return "Software 2020！";
    }
}
