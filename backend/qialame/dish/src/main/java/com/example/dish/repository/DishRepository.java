package com.example.dish.repository;

import com.example.dish.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface DishRepository extends JpaRepository<Dish,Integer>
{
    Optional<Dish> findById(Integer id);

    List<Dish> findByDishNameLike(String dishName);

    List<Dish> findByTasteLike(String taste);

    List<Dish> findByDishType(String dishType);

    List<Dish> findByLocation(String location);
}
