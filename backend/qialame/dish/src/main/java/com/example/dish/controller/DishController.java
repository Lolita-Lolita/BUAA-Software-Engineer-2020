package com.example.dish.controller;

import com.example.dish.entity.Dish;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DishController
{

    @RequestMapping("/getDish")
    public Dish getDish()
    {
        return new Dish(1,"锅包肉",52.00,"酸","荤菜","学二食堂");
    }
}
