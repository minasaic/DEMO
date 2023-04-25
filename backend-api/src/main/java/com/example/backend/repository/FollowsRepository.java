// package com.example.backend.repository;

// import org.springframework.data.jpa.repository.JpaRepository;

// import com.example.backend.domain.Follows;

// public interface FollowsRepository extends JpaRepository<Follows, Integer>{
// }
package com.example.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.backend.domain.Follows;

public interface FollowsRepository extends JpaRepository<Follows, Integer>{
    Integer countByFollowingid(Integer id);
    Integer countByFollowerid(Integer id);
    List<Follows> findByFollowingid(Integer id);
}