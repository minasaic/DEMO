package com.example.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.domain.Posts;

public interface PostsRepository extends JpaRepository<Posts, Integer>{
    // Optional<Posts> findById(Integer id);
}

