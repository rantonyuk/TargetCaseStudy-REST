package com.interview.casestudy.rest.service;

import java.util.List;

import com.interview.casestudy.rest.model.Product;

public interface ProductService {
	
	List<Product> getAll();
	
	Product getById(Integer id);

	void saveOrUpdateProduct(Product product);

	void deleteProductById(Integer id);
}
