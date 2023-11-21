package com.diplomado.homework.repositories;

import com.diplomado.homework.domain.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
    List<UserRole> findAllByRoles_Id(Integer roleId);
    List<UserRole>findAllByUsers_Id(Long userId);
}