package com.fql.repository;

import com.fql.entity.OurTeamModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OurTeamRepository extends JpaRepository<OurTeamModel,Integer> {
}
