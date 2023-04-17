package com.example.backend.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.domain.Follows;

public interface FollowsRepository extends JpaRepository<Follows, Integer>{
    int[] findFollower_IdById(Integer id);
}