package com.example.dish.controller;

import com.example.dish.entity.Dish;
import com.example.dish.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.GeneratedValue;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("dish")
public class DishController
{
    @Autowired
    private DishService dishService;

    @GetMapping("/id/{id}")
    public ResponseEntity<Optional<Dish>> findByID(@PathVariable("id") Integer id)
    {
        return ResponseEntity.ok(dishService.findById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Dish>> findByDishNameLike(@PathVariable("name") String dishName)
    {
        return ResponseEntity.ok(dishService.findByDishNameLike("%"+dishName+"%"));
    }

    @GetMapping("/taste/{taste}")
    public ResponseEntity<List<Dish>> findByTasteLike(@PathVariable("taste") String taste)
    {
        return ResponseEntity.ok(dishService.findByTasteLike("%"+taste+"%"));
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<Dish>> findByDishType(@PathVariable("type") String dishType)
    {
        return ResponseEntity.ok(dishService.findByDishType(dishType));
    }

    @GetMapping("/location/{location}")
    public ResponseEntity<List<Dish>> findByLocation(@PathVariable("location") String location)
    {
        return ResponseEntity.ok(dishService.findByLocation(location));
    }
}
