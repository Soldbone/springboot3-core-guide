package com.springboot.jpa.data.repository;

import com.springboot.jpa.data.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;  // <- 철자 확인!

public interface ProductRepository extends JpaRepository<Product, Long> {
}
