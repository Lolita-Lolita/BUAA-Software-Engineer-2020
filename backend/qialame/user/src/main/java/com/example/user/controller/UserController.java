package com.example.user.controller;

import com.example.dish.entity.Dish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class UserController
{
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/showDish")
    public Dish showDish()
    {
        Dish dish = restTemplate.getForObject("http://dish/getDish",Dish.class);
        return dish;
    }
}
