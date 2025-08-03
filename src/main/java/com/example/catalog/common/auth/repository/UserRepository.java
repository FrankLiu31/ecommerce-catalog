package com.example.catalog.common.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import com.example.catalog.common.auth.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}