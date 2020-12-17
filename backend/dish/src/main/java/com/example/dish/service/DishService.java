package com.example.dish.service;

import com.example.dish.entity.Dish;
import com.example.dish.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DishService
{
    @Autowired
    private DishRepository dishRepository;

    public Optional<Dish> findById(Integer id) {
        return dishRepository.findById(id);
    }

    public List<Dish> findByDishNameLike(String dishName) {
        return dishRepository.findByDishNameLike(dishName);
    }

    public List<Dish> findByTasteLike(String taste) {
        return dishRepository.findByTasteLike(taste); }

    public List<Dish> findByDishType(String dishType) {
        return dishRepository.findByDishType(dishType);
    }

    public List<Dish> findByLocation(String location) {
        return dishRepository.findByLocation(location);
    }

    public void delete(Integer id) {
        dishRepository.deleteById(id);
    }

    public Dish save(Dish dish) {
        return dishRepository.save(dish);
    }
}
