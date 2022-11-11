package com.fql.repository;

import com.fql.entity.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerModel,Integer> {
}
