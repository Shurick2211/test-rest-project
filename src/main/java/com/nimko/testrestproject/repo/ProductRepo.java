package com.nimko.testrestproject.repo;

import com.nimko.testrestproject.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Long> {

}
