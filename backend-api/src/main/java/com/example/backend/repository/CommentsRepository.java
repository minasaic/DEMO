package com.example.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.domain.Comments;

public interface CommentsRepository extends JpaRepository<Comments, Integer>{
    List<Comments> findByPostid(Integer id);

    List<Comments> findByUserid(Integer id);
}
