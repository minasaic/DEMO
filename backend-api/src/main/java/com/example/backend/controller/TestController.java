package com.example.backend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.domain.LoginRequest;
import com.example.backend.domain.User;
import com.example.backend.service.TestService;

@RestController
@CrossOrigin
class TestController{
    @Autowired
    TestService testService;

    @GetMapping(path="/testes/{id}")
    public User getUsers(@PathVariable("id") Integer id){
        return testService.getUser(id);
    }

    @PostMapping(path="/aaa")
    public User getUsers2(@RequestBody Integer id){
        return testService.getUser(id);
    }

    @PostMapping(path="/login")
    public boolean loginUser(@RequestBody LoginRequest login){
        return testService.loginUser(login.getUsername(), login.getPassword());
    }

}
