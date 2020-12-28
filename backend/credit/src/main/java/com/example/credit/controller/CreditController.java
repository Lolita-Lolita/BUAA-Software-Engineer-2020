package com.example.credit.controller;

import com.demo.config.annotation.Authority;
import com.demo.entity.User;
import com.demo.repository.UserRepository;
import com.example.credit.entity.Credit;
import com.example.credit.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/credit")
public class CreditController {

    @Autowired
    private CreditService creditService;
    private @Autowired(required = false) UserRepository userRepository;

    @PreAuthorize("hasRole('user') or hasRole('admin')")
    @GetMapping("/get-credit/{dishID}")
    public ResponseEntity<List<Credit>> findByDishID(@PathVariable Integer dishID) {
        return ResponseEntity.ok(creditService.findByDishID(dishID));
    }

    @PreAuthorize("hasRole('user') or hasRole('admin')")
    @PostMapping("set-credit")
    public ResponseEntity<Credit> setCredit(Credit credit) {
        return ResponseEntity.ok(creditService.setCredit(credit));
    }

    @StreamListener(value = "user", condition = "headers['operate'] == 'save'")
    public void saveUser(User user) {
        userRepository.save(user);
        System.out.println(user);
    }

    @StreamListener(value = "user", condition = "headers['operate'] == 'delete'")
    public void deleteUser(String user) {
        userRepository.deleteById(Long.parseLong(user));
        System.out.println(user);
    }

}
