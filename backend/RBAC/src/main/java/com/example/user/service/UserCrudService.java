package com.example.user.service;

import com.example.user.entity.UserList;
import com.example.user.repository.UserCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserCrudService {

    @Autowired
    private UserCrudRepository userCrudRepository;

    public Optional<UserList> findById(Integer id) {
        return userCrudRepository.findById(id);
    }

    public List<UserList> findByUserNameLike(String name) {
        return userCrudRepository.findByUserNameLike(name);
    }

    public List<UserList> findByRoleLike(String role) {
        return userCrudRepository.findByRoleLike(role);
    }

    public void delete(Integer id) {
        userCrudRepository.deleteById(id);
    }

    public UserList save(UserList userList) {
        return userCrudRepository.save(userList);
    }
}
