package com.example.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.domain.Testes;
import com.example.backend.service.TestService;

@RestController
@CrossOrigin
class TestController{
    @Autowired
    TestService testService;

    @GetMapping(path="/testes/{id}")
    public Testes getTestes(@PathVariable("id") Integer id){
        return testService.getTestes(id);
    }

    @GetMapping(path="/hello")
    public String hello(){
        return "hello world!!!";
    }

}
