package com.example.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.domain.User;

public interface UserRepository extends JpaRepository<User, Integer>{
    Optional<User> findByName(String name);

    List<User> findByNameLike(String string);
}

