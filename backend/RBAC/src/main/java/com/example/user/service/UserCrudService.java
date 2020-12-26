package com.example.user.service;

import com.example.user.entity.UserCrud;
import com.example.user.repository.UserCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserCrudService {

    @Autowired
    private UserCrudRepository userCrudRepository;

    public Optional<UserCrud> findById(Integer id) {
        return userCrudRepository.findById(id);
    }

    public List<UserCrud> findByUserNameLike(String name) {
        return userCrudRepository.findByUserNameLike(name);
    }

    public List<UserCrud> findByRoleLike(String role) {
        return userCrudRepository.findByRoleLike(role);
    }

    public void delete(Integer id) {
        userCrudRepository.deleteById(id);
    }

    public UserCrud save(UserCrud userCrud) {
        return userCrudRepository.save(userCrud);
    }
}
