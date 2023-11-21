package com.diplomado.homework.services;

import com.diplomado.homework.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDTO> getUsers();
    Optional<UserDTO> getUser(Long userId);
    UserDTO createUser(UserDTO user);
    void deleteUser(Long userId);

}