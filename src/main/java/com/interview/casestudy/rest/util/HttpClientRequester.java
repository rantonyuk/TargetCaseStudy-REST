package com.interview.casestudy.rest.util;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author roksolan antonyuk
 *
 */
public class HttpClientRequester {
	
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private String url = "https://redsky.target.com/v3/pdp/tcin/";
	private String pathParam = "?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics&key=candidate";
	
	/**
	 * Parse HttpResponse body and obtain product name based on product id
	 * 
	 * @param productId - product id
	 * @return product make id product found or empty string if it is not
	 */
	public String getProductName(Integer productId) {
		ObjectMapper objMapper = new ObjectMapper();
		try {
			String response = makeHttpCall(productId);
			Map<String, Map> responseMap = objMapper.readValue(response, Map.class);
			Map<String,Map> productMap = responseMap.get("product");
	        Map<String,Map> itemMap = productMap.get("item");
	        Map<String,String> prodDescrMap = itemMap.get(("product_description"));
        	
	        return prodDescrMap.get("title");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * Use HttpClient to make call to external resource and return response body
	 * 
	 * @param productId - product id
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public String makeHttpCall(Integer productId) throws IOException, InterruptedException {
		// create a client
		HttpClient client = HttpClient.newHttpClient();
		
		// create a request
		 HttpRequest request = HttpRequest.newBuilder(
		       URI.create(url + productId + pathParam))
		   .header("accept", "application/json")
		   .build();

		// use the client to send the request
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		logger.info(response.toString());
		
		// the response:
		return response.body();
	}

}