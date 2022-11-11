package com.fql.repository;

import com.fql.entity.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductModel,Integer> {
}
