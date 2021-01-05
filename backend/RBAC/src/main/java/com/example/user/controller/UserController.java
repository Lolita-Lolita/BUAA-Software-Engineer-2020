package com.example.user.controller;

import com.demo.config.annotation.Authority;
import com.example.user.entity.UserCrud;
import com.example.user.service.UserCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/userCrud")
public class UserController
{
    @Autowired
    //private RestTemplate restTemplate;
    private UserCrudService userService;

    @PreAuthorize("hasRole('admin')")
    @GetMapping("/id/{id}")
    public ResponseEntity<Optional<UserCrud>> findByID(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(userService.findById(id));
    }


    @PreAuthorize("hasRole('admin')")
    @RequestMapping("/delete/{id}")
    public String deleteById(@PathVariable("id") Integer id) {
        userService.delete(id);
        return "User Delete Success!";
    }

    @PreAuthorize("hasRole('admin')")
    @GetMapping("/name/{Username}")
    public ResponseEntity<List<UserCrud>> findByUserNameLike(@PathVariable("Username") String name) {
        return ResponseEntity.ok(userService.findByUserNameLike(name));
    }

    @PreAuthorize("hasRole('admin')")
    @GetMapping("/role/{role}")
    public ResponseEntity<List<UserCrud>> findByUserRole(@PathVariable("role") String role) {
        return ResponseEntity.ok(userService.findByRoleLike(role));
    }

    @PreAuthorize("hasRole('admin')")
    @PostMapping("/save")
    public ResponseEntity<UserCrud> save(UserCrud userCrud) {
        return ResponseEntity.ok(userService.save(userCrud));
    }

    @PreAuthorize("hasRole('admin')")
    @PostMapping("/update")
    public ResponseEntity<UserCrud> updateNewUserNameAndRole(UserCrud userCrud, String newUserName, String newRole) {
        return ResponseEntity.ok(userService.UpdateNameAndRole(userCrud, newUserName, newRole));
    }

    @PreAuthorize("hasRole('admin')")
    @GetMapping("/findAll")
    public ResponseEntity<List<UserCrud>> findAll(){
        return ResponseEntity.ok(userService.findAll());
    }
}
