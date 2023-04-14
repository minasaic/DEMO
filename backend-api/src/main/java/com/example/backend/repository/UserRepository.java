package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.domain.User;

public interface UserRepository extends JpaRepository<User, Integer>{
    User findByName(String name);
}

