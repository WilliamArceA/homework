package com.diplomado.homework.services;

import com.diplomado.homework.dto.UserDetailDTO;

import java.util.List;
import java.util.Optional;

public interface UserDetailService {

    List<UserDetailDTO> getUserDetails();
    Optional<UserDetailDTO> getUserDetail(Long userId);
    UserDetailDTO createUserDetail(UserDetailDTO detail);
    void deleteUserDetail(Long userId);

}