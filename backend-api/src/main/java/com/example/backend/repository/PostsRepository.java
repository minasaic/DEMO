package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.domain.Posts;

public interface PostsRepository extends JpaRepository<Posts, Integer>{
    String findPathById(Integer id);
}

