package com.example.dish.repository;

import com.example.dish.controller.params.DishSearchParams;
import com.example.dish.entity.Dish;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.swing.text.html.HTMLDocument;
import java.awt.print.Pageable;
import java.util.List;

public interface DishRepository extends JpaRepository<Dish,Integer>, JpaSpecificationExecutor<Dish>
{
    List<Dish> findByDishNameLike(String dishName);

    List<Dish> findByTasteLike(String taste);

    List<Dish> findByDishType(String dishType);

    List<Dish> findByLocation(String location);

    List<Dish> findAll();
}
