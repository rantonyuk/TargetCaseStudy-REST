package com.interview.casestudy.rest.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interview.casestudy.rest.model.Product;
import com.interview.casestudy.rest.repository.ProductRepository;
import com.interview.casestudy.rest.util.HttpClientRequester;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ProductRepository productRepository;
	 
	/**
	 * Get product by id. 
	 * Price data will come from db and name from external resource. 
	 * Function will populates all the fields that it can. 
	 */
	@Override 
	public Product getById(Integer id) { 
		Product product = new Product();
		product.setId(id);
		
		//get product from db
		Optional<Product> productDB = productRepository.findById(id);
		if(productDB.isPresent()){
			product = productDB.get();
			logger.info("Get product {} from db", product);
		}
		
		//get name from external source
		product.setName(getName(id));
		
		logger.info("Returning product ", product);
		
		return product; 
	}
	
	private String getName(Integer id) {
		HttpClientRequester httpCall = new HttpClientRequester();
		String productName = httpCall.getProductName(id);
		logger.info("Get product name {} ", productName);
		return productName;
	}
	
	 
	@Override
	public void saveOrUpdateProduct(Product product) {
		productRepository.save(product);
	}

	@Override
	public void deleteProductById(Integer id) {
		productRepository.deleteById(id);
	}

	/**
	 * Return all the products from db. 
	 */
	@Override
	public List<Product> getAll() {
		List<Product> products = productRepository.findAll();
		for(Product product : products) {
			product.setName(getName(product.getId()));
		}
		return products;
	}
}
