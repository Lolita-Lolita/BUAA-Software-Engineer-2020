package com.example.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class UserController
{
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/id")
    public String ByID()
    {
        String dish = restTemplate.getForObject("http://dish/id",String.class);
        return dish;
    }

    @RequestMapping("/name")
    public String ByName()
    {
        String dish = restTemplate.getForObject("http://dish/name",String.class);
        return dish;
    }
}
