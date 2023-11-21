package com.diplomado.homework.services;

import com.diplomado.homework.dto.UserRoleDTO;

import java.util.List;
import java.util.Optional;

public interface UserRoleService {
    List<UserRoleDTO> getUserRoles();
    Optional<UserRoleDTO>getUserRole(Integer userRoleId);
    List<UserRoleDTO> getUsersByRole(Integer roleId);
    List<UserRoleDTO> getRolesByUser(Long userId);
    List<UserRoleDTO> createUserRoles(UserRoleDTO... userRole);
    void deleteUserRole(Integer userRoleId);
    UserRoleDTO setInactive(Integer userRoleId);
}