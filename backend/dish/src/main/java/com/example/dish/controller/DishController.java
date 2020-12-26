package com.example.dish.controller;

import com.example.dish.controller.params.DishSearchParams;
import com.example.dish.entity.Dish;
import com.example.dish.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dish")
public class DishController {
    @Autowired
    private DishService dishService;
    /*

    private static Logger logger= LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    @GetMapping("/getLog")
    public void testLog(){
        for(int i=0;i<3;i++){
            // 记录trace级别的信息
            logger.trace("log4j2日志输出：This is trace message.");
            // 记录debug级别的信息
            logger.debug("log4j2日志输出：This is debug message.");
            // 记录info级别的信息
            logger.info("log4j2日志输出：This is info message.");
            // 记录error级别的信息
            logger.error("log4j2日志输出：This is error message.");
        }

    }

    */
    @GetMapping("/id/{id}")
    public ResponseEntity<Optional<Dish>> findByID(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(dishService.findById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Dish>> findByDishNameLike(@PathVariable("name") String dishName) {
        return ResponseEntity.ok(dishService.findByDishNameLike("%"+dishName+"%"));
    }

    @GetMapping("/taste/{taste}")
    public ResponseEntity<List<Dish>> findByTasteLike(@PathVariable("taste") String taste) {
        return ResponseEntity.ok(dishService.findByTasteLike("%"+taste+"%"));
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<Dish>> findByDishType(@PathVariable("type") String dishType) {
        return ResponseEntity.ok(dishService.findByDishType(dishType));
    }

    @GetMapping("/location/{location}")
    public ResponseEntity<List<Dish>> findByLocation(@PathVariable("location") String location) {
        return ResponseEntity.ok(dishService.findByLocation(location));
    }

    @PostMapping("/findAll")
    public ResponseEntity<Page<Dish>> findAllDishByCondition(Pageable pageable, DishSearchParams params) {
        return ResponseEntity.ok(dishService.findAllDishByCondition(pageable,params));
    }

    @PostMapping("/save")
    public ResponseEntity<Dish> save(@RequestBody Dish dish) {
        return ResponseEntity.ok(dishService.save(dish));
    }

}
