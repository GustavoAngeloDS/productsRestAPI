package com.personal.projects.products.restapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class ProductController {
	
	@Autowired
	ProductRepository productRepository;
	
	@GetMapping("/products")
	@ApiOperation(value="Return all products")
	public List<Product> findAll(){
		return productRepository.findAll();
	}
	
	@GetMapping("/products/{id}")
	@ApiOperation(value="Return a specific product")
	public ResponseEntity<Product> findById(@PathVariable(value="id") long id) {
		Optional<Product> product = productRepository.findById(id);
		
		if(product.isPresent())
			return new ResponseEntity<Product>(product.get(), HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/products")
	@ApiOperation(value="Save a new product")
	public Product saveProduct(@RequestBody Product product) {
		return productRepository.save(product);
	}
	
	@DeleteMapping("/products")
	@ApiOperation(value="Delete a product")
	public ResponseEntity<Product> deleteProduct(@RequestBody Product product) {
		Optional<Product> productToDelete = productRepository.findById(product.getId());
		
		if(productToDelete.isPresent()) {
			productRepository.delete(product);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/products")
	@ApiOperation(value="Update a product")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
		Optional<Product> oldProduct = productRepository.findById(product.getId());
		if(oldProduct.isPresent()) {
			productRepository.save(product);
			return new ResponseEntity<Product>(product, HttpStatus.OK);
		}
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
