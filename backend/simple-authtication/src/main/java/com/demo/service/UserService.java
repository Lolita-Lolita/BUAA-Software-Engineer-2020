package com.demo.service;

import com.demo.entity.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findByUsername(String username);
}
