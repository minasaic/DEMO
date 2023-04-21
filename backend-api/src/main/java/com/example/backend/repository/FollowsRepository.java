// package com.example.backend.repository;

// import org.springframework.data.jpa.repository.JpaRepository;

// import com.example.backend.domain.Follows;

// public interface FollowsRepository extends JpaRepository<Follows, Integer>{
// }
package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.backend.domain.Follows;

public interface FollowsRepository extends JpaRepository<Follows, Integer>{
    Integer countByFollowing(Integer id);
    Integer countByFollower(Integer id);
    // Optional<Follows> findByFollowingd(Integer id);
}