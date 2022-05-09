package com.example.demo.spring.security.jwt.repo;

import com.example.demo.spring.security.jwt.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("roleRepo")
public interface RoleRepo extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
