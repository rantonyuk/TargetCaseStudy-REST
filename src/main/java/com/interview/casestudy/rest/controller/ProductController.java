package com.interview.casestudy.rest.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.interview.casestudy.rest.model.Product;
import com.interview.casestudy.rest.service.ProductService;

/**
 * Controller class defines REST end points
 * 
 * @author roksolana antonyuk
 *
 */
@RestController
@RequestMapping("/products")
public class ProductController {
	
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private ProductService productService;
	
	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> getProducts() {
		
		List<Product> product = productService.getAll();
		
		return new ResponseEntity<List<Product>>(product, HttpStatus.OK);
    }
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> getProduct(@PathVariable("id") Integer id) {
		
		Product product = productService.getById(id);
		
		return new ResponseEntity<Product>(product, HttpStatus.OK);
    }
	
	@PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> saveOrUpdateProduct(@RequestBody Product product) {
				
		logger.info("Saving product {}", product);
		productService.saveOrUpdateProduct(product);
		
		return new ResponseEntity<>("Product added/updated successfully", HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteProduct(@PathVariable Integer id) {
		
		productService.deleteProductById(id);
		logger.info("Deleting product by id {}", id);
		
		return new ResponseEntity<>("Product deleted successfully", HttpStatus.OK);
	}
}
