package com.example.dish.repository;

import com.example.dish.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface DishRepository extends JpaRepository<Dish,Integer>
{
    List<Dish> findByDishNameLike(String dishName);

    List<Dish> findByTasteLike(String taste);

    List<Dish> findByDishType(String dishType);

    List<Dish> findByLocation(String location);

    //下列方法供后台使用之。

    //增加

}
