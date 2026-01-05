package com.javapp.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javapp.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    public boolean existsByEmail(String email);


    public boolean existsByUsername(String username);
}
