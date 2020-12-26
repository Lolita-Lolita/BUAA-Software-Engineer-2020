package com.example.user.repository;

import com.example.user.entity.UserCrud;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserCrudRepository extends JpaRepository<UserCrud,Integer> {

    List<UserCrud> findByUserNameLike(String name);

    List<UserCrud> findByRoleLike(String role);

}
