package com.example.dish.service;

import com.example.dish.controller.params.DishSearchParams;
import com.example.dish.entity.Dish;
import com.example.dish.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<Dish> findByTasteLike(String taste) { return dishRepository.findByTasteLike(taste); }

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

    public Page<Dish> findAllDishByCondition(Pageable pageable, DishSearchParams param) {

        Specification<Dish> sp = (root, criteriaQuery, criteriaBuilder) -> {

            List<Predicate> predicates = new LinkedList<>();

            StringBuilder builder;
            if (Objects.nonNull(param.getDishName())) {
                predicates.add(criteriaBuilder.like(root.get("dishName").as(String.class), "%" + param.getDishName() + "%"));
            }

            if (Objects.nonNull(param.getTaste())) {
                String temp = param.getTaste().stream().collect(Collectors.joining("|"));
                temp = temp.replaceAll("\\(", "\\\\(");
                temp = temp.replaceAll("\\)", "\\\\)");
                builder = new StringBuilder();
                String condition = builder.append("(").append(temp).append(")").toString();
                Expression<Boolean> re = criteriaBuilder.function("regexp_like", Boolean.class, root.get("taste"), criteriaBuilder.literal(condition));
                predicates.add(criteriaBuilder.isTrue(re));
            }

            if (Objects.nonNull(param.getDishType())) {
                //predicates.add(criteriaBuilder.like(root.get("dishType").as(String.class), "%" + param.getDishType() + "%"));
                String temp = param.getDishType().stream().collect(Collectors.joining("|"));
                temp = temp.replaceAll("\\(","\\\\(");
                temp = temp.replaceAll("\\)","\\\\)");
                builder = new StringBuilder();
                String condition = builder.append("(").append(temp).append(")").toString();
                Expression<Boolean> re = criteriaBuilder.function("regexp_like", Boolean.class, root.get("dishType"),criteriaBuilder.literal(condition));
            }

            if (Objects.nonNull(param.getLocation())) {
                //predicates.add(criteriaBuilder.like(root.get("location").as(String.class), "%" + param.getLocation() + "%"));
                String temp = param.getLocation().stream().collect(Collectors.joining("|"));
                temp = temp.replaceAll("\\(","\\\\(");
                temp = temp.replaceAll("\\)","\\\\)");
                builder = new StringBuilder();
                String condition = builder.append("(").append(temp).append(")").toString();
                Expression<Boolean> re = criteriaBuilder.function("regexp_like", Boolean.class, root.get("location"), criteriaBuilder.literal(condition));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };

        Page<Dish> dish = dishRepository.findAll(sp, pageable);

        return dish;
    }

}
