package com.interview.casestudy.rest.model;

import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author roksolana antonyuk
 *
 */
@Document(collection = "products")
public class Product {
	@Id
    private Integer id;
    private String name; 
	@JsonProperty(value = "current_price")
    private Map<String, String> currentPrice;
	
	public Product() {}
	
    public Product(Integer id, String name, Map<String,String> currentPrice) {
		this.id = id;
		this.name = name;
		this.currentPrice = currentPrice;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String,String> getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(Map<String,String> currentPrice) {
		this.currentPrice = currentPrice;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", currentPrice=" + currentPrice + "]";
	}

}
