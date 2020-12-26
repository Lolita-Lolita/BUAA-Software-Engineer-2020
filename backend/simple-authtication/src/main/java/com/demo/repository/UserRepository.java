package com.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.entity.User;

/**
 * 用于实现 User 数据库crud操作的 Repository
 */
public interface UserRepository extends JpaRepository<User, Long> {

	//通过 Username 查询用户信息
	Optional<User> findByUsername(String username);
}
