package com.example.credit.repository;

import com.example.credit.entity.Credit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditRepository extends JpaRepository<Credit,Integer> {

    List<Credit> findByDishID(Integer did);

}
