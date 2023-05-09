package com.example.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.domain.Posts;
public interface PostsRepository extends JpaRepository<Posts, Integer>{
    // @Query("SELECT * FROM Posts p JOIN User u ON u.id = p.userid")
    List<Posts> findByUserid(Integer user_id);
    List<Posts> findByCaptionLike(String caption);
    Integer findByLikes(Integer likes);
    Integer countByUserid(Integer id);
}

