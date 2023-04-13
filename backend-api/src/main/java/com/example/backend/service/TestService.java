package com.example.backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.domain.Testes;
import com.example.backend.repository.TestRepository;

@Service
public class TestService {
    @Autowired
    TestRepository testRepository;

    public Testes getTestes(Integer id){
        Optional<Testes> testesOptional =testRepository.findById(id);
        if(testesOptional.isPresent()){
            return testesOptional.get();
        }
        return null;
    }
}
