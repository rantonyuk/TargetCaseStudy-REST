package com.interview.casestudy.rest.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.interview.casestudy.rest.model.Product;

/**
 * @author roksolana antonyuk
 *
 */
public interface ProductRepository extends MongoRepository<Product, Integer> {
	
}
