package com.mcenk.percentileapi.repository;


import com.mcenk.percentileapi.model.Child;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChildRepository extends JpaRepository<Child,Long> {
}
