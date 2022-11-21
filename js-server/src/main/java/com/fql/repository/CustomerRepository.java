package com.fql.repository;

import com.fql.entity.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<CustomerModel,Integer> {
}
