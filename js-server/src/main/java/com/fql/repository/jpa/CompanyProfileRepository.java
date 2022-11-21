package com.fql.repository.jpa;

import com.fql.entity.CompanyProfileModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyProfileRepository extends JpaRepository<CompanyProfileModel,Integer> {
}
