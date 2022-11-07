package com.example.securityproject.repo;

import com.example.securityproject.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Role findByname(String name);
}

