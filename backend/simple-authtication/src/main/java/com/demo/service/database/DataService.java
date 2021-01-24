package com.demo.service.database;

import com.demo.entity.User;
import com.demo.repository.UserRepository;
import com.demo.service.UserService;
import java.util.Optional;

public class DataService implements UserService {

    private UserRepository repository;

    public DataService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return repository.findByUsername(username);
    }
}
