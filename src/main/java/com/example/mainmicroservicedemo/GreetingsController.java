package com.example.mainmicroservicedemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/greetings")
public class GreetingsController {

    @Autowired
    GreetingService greetingService;

    @GetMapping
    public List<Greeting> getAllGreetings(){

        return greetingService.getAllGreetings();
    }
}
