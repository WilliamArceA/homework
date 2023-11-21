package com.diplomado.homework.repositories;

import com.diplomado.homework.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}