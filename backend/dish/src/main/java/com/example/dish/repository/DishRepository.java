package com.example.dish.repository;

import com.example.dish.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DishRepository extends JpaRepository<Dish,Integer>
{
    List<Dish> findByDishNameLike(String dishName);

    List<Dish> findByTasteLike(String taste);

    List<Dish> findByDishType(String dishType);

    List<Dish> findByLocation(String location);

    //下列方法供后台使用之。

}
