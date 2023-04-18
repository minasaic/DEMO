package com.example.backend.repository;

<<<<<<< Updated upstream
=======

import java.util.Optional;

>>>>>>> Stashed changes
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.domain.Follows;

public interface FollowsRepository extends JpaRepository<Follows, Integer>{
<<<<<<< Updated upstream
=======
    Optional<Follows> findByFollowingd(Integer id);
>>>>>>> Stashed changes
}