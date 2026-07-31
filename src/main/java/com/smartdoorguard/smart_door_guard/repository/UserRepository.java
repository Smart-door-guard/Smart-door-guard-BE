package com.smartdoorguard.smart_door_guard.repository;

import com.smartdoorguard.smart_door_guard.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
