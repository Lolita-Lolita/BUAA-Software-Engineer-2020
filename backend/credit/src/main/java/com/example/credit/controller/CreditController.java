package com.example.credit.controller;

import com.example.credit.entity.Credit;
import com.example.credit.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@RestController
@RequestMapping("/credit")
public class CreditController {

    @Autowired
    private CreditService creditService;

    @GetMapping("/get-credit/{dishID}")
    public ResponseEntity<List<Credit>> findByDishID(@PathVariable Integer dishID) {
        return ResponseEntity.ok(creditService.findByDishID(dishID));
    }

    @PostMapping("set-credit")
    public ResponseEntity<Credit> setCredit(Credit credit) {
        return ResponseEntity.ok(creditService.setCredit(credit));
    }

}
