package com.example.backend.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.domain.Follows;

public interface FollowsRepository extends JpaRepository<Follows, Integer>{
    // Optional<Follows> findByFollowingd(Integer id);
}