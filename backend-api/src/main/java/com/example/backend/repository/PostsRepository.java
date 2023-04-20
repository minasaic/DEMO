package com.example.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.domain.Posts;

public interface PostsRepository extends JpaRepository<Posts, Integer>{
    List<Posts> findByUserid(Integer user_id);
}

