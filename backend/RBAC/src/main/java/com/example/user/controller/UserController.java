package com.example.user.controller;

import com.demo.entity.User;
import com.example.user.entity.UserList;
import com.example.user.service.UserCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController
{
    @Autowired
    private RestTemplate restTemplate;
    private UserCrudService userService;

    @GetMapping("/id/{id}")
    public ResponseEntity<Optional<UserList>> findByID(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(userService.findById(id));
    }


    @RequestMapping("/delete/{id}")
    public String deleteById(@PathVariable("id") Integer id) {
        userService.delete(id);
        return "User Delete Success!";
    }

    @GetMapping("/name/{userName}")
    public ResponseEntity<List<UserList>> findByUserName(@PathVariable("userName") String name) {
        return ResponseEntity.ok(userService.findByUserNameLike(name));
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<List<UserList>> findByUserRole(@PathVariable("role") String role) {
        return ResponseEntity.ok(userService.findByRoleLike(role));
    }

    @PostMapping("/save")
    public ResponseEntity<UserList> save(@RequestBody UserList userList) {
        return ResponseEntity.ok(userService.save(userList));
    }
}
