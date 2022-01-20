package com.example.demo.repository;

import com.example.demo.entity.Role;
import com.example.demo.entity.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRole(RoleEnum roleEnum);
}
