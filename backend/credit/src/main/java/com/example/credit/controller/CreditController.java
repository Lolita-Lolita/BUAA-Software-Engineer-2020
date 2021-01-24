package com.example.credit.controller;

import com.demo.config.annotation.Authority;
import com.demo.repository.UserRepository;
import com.example.credit.entity.Credit;
import com.example.credit.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Authority
    @GetMapping("/get-credit/{dishID}")
    public ResponseEntity<List<Credit>> findByDishID(@PathVariable Integer dishID) {
        return ResponseEntity.ok(creditService.findByDishID(dishID));
    }

    @Authority
    @GetMapping("/get-averageCredit/{dishID}")
    public ResponseEntity<Credit> findAverageCreditByDishID(@PathVariable("dishID") Integer dishID) {
        Credit credit = new Credit();
        List<Credit> creditList = creditService.findByDishID(dishID);
        credit.setLookCredit(creditService.CalAverageLookCredit(creditList));
        credit.setSmellCredit(creditService.CalAverageSmellCredit(creditList));
        credit.setTasteCredit(creditService.CalAverageTasteCredit(creditList));
        credit.setAverageCredit(creditService.CalAverageCredit(creditList));
        return ResponseEntity.ok(credit);
    }

    @PreAuthorize("hasRole('user') or hasRole('admin')")
    @PostMapping("/set-credit")
    public ResponseEntity<Credit> setCredit(Credit credit, Integer dishID) {
        credit.setDishID(dishID);
        return ResponseEntity.ok(creditService.setCredit(credit));
    }

}
