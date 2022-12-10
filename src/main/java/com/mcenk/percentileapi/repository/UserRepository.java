package com.mcenk.percentileapi.repository;

import com.mcenk.percentileapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;


public interface UserRepository  extends JpaRepository <User, Long> {
    Optional<User> findUserByUsername(String username);

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
