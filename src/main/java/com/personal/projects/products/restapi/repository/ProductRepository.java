package com.personal.projects.products.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.personal.projects.products.restapi.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
}
