package com.example.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.domain.User;

public interface UserRepository extends JpaRepository<User, Integer>{
    User findByName(String name);

    List<User> findByNameLike(String string);
}

