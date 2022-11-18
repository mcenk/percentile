package com.mcenk.percentileapi.repository;

import com.mcenk.percentileapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository <User, Long> {
}
