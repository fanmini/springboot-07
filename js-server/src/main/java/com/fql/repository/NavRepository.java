package com.fql.repository;

import com.fql.entity.NavModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NavRepository extends JpaRepository<NavModel,Integer> {
}
