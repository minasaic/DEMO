package com.example.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.example.backend.domain.Likes;

public interface LikesRepository extends JpaRepository<Likes, Integer>{

    List<Likes> findByUserid(Integer id);

    Optional<Likes> findByPostidAndUserid(Integer postId, Integer userId);

    @Transactional
    int deleteAllByPostid(Integer postId);
 
}
