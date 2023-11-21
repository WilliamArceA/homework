package com.diplomado.homework.repositories;

import com.diplomado.homework.domain.entities.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDetailRepository extends JpaRepository<UserDetail, Long> {

    Optional<UserDetail> findByUsers_Id(Long userId);
    void deleteByUsers_Id(Long userId);
}