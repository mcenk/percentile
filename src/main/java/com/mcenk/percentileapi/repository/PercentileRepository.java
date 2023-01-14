package com.mcenk.percentileapi.repository;

import com.mcenk.percentileapi.model.PercentileCalc;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PercentileRepository extends JpaRepository<PercentileCalc,Long> {
 }
