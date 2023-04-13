package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.domain.Testes;

public interface TestRepository extends JpaRepository<Testes, Integer>{
}
