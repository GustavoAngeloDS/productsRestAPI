package com.personal.projects.products.restapi.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personal.projects.products.restapi.models.Product;
import com.personal.projects.products.restapi.repository.ProductRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/api")
@Api(value="API Rest - Products")
@CrossOrigin(origins="*")
public class ProductResource {
	
	@Autowired
	ProductRepository productRepository;
	
	@GetMapping("/products")
	@ApiOperation(value="Return all products")
	public List<Product> findAll(){
		return productRepository.findAll();
	}
	
	@GetMapping("/products/{id}")
	@ApiOperation(value="Return a specific product")
	public Optional<Product> findById(@PathVariable(value="id") long id) {
		return productRepository.findById(id);
	}
	
	@PostMapping("/products")
	@ApiOperation(value="Save a new product")
	public Product saveProduct(@RequestBody Product product) {
		return productRepository.save(product);
	}
	
	@DeleteMapping("/products")
	@ApiOperation(value="Delete a product")
	public void deleteProduct(@RequestBody Product product) {
		productRepository.delete(product);
	}
	
	@PutMapping("/products")
	@ApiOperation(value="Update a product")
	public Product updateProduct(@RequestBody Product product) {
		return productRepository.save(product);
	}
}
