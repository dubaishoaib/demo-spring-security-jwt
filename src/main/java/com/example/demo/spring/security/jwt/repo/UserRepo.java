package com.example.demo.spring.security.jwt.repo;

import com.example.demo.spring.security.jwt.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userRepo")
public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
