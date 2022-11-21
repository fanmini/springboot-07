package com.fql.repository.jpa;

import com.fql.entity.CompanyContactModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Qian
 */
public interface CompanyContactRepository extends JpaRepository<CompanyContactModel,Integer> {

}
