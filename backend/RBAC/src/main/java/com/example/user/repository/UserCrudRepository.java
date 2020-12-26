package com.example.user.repository;

import com.example.user.entity.UserList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserCrudRepository extends JpaRepository<UserList,Integer> {

    List<UserList> findByUserNameLike(String name);

    List<UserList> findByRoleLike(String role);

}
