package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.domain.Comments;

public interface CommentsRepository extends JpaRepository<Comments, Integer>{
}
