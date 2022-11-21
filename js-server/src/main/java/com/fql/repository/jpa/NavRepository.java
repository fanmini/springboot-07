package com.fql.repository.jpa;

import com.fql.entity.NavModel;
import com.fql.entity.ResultModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NavRepository extends JpaRepository<NavModel,Integer> {
    List<NavModel> findAllByType(Integer type);
}
