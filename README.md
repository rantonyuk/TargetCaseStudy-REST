## Case Study:
myRetail RESTful servicemyRetail is a rapidly growing company with HQ in Richmond, VA and over 200 stores across the east coast. myRetail wants to make its internal data available to any number of client devices, from myRetail.com to native mobile apps. 

The goal for this exercise is to create an end-to-end Proof-of-Concept for a products API, which will aggregate product data from multiple sources and return it as JSON to the caller. Your goal is to create a RESTful service that can retrieve product and price details by ID. The URL structure is up to you to define, but try to follow some sort of logical convention.Build an application that performs the following actions: 
Responds to an HTTP GET request at /products/{id} and delivers product data as JSON (where {id} will be a number. 

Example product IDs: 13860428, 54456119, 13264003, 12954218) 
Example response: {"id":13860428,"name":"The Big Lebowski (Blu-ray) (Widescreen)","current_price":{"value": 13.49,"currency_code":"USD"}}

Performs an HTTP GET to retrieve the product name from an external API. (For this exercise the data will come from redsky.target.com, but let’s just pretend this is an internal resource hosted by myRetail) 
Example: https://redsky.target.com/v3/pdp/tcin/13860428?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics&key=candidate

Reads pricing information from a NoSQL data store and combines it with the product id and name from the HTTP request into a single response. 

BONUS: Accepts an HTTP PUT request at the same path (/products/{id}), containing a JSON request body similar to the GET response, and updates the product’s price in the data store. 

## How to run: 
1. Run Mongodb in command line by: <br />
mongod <br />
mongo
2. Run com.interview.casestudy.rest.RestApplication as java application 

## How to test:
Use Postman to

1. Verify nothing in db <br />
GET: http://localhost:8081/products/

2. Create object in db with price value <br />
POST: http://localhost:8081/products/
{
   "id":13860428,
   "current_price":{
      "value":11,
      "currency_code":"USD"
   }
}

3. Perform get that will merge data from db and external call <br />
GET: http://localhost:8081/products/13860428

Expected result: 
{
    "id": 13860428,
    "name": "The Big Lebowski (Blu-ray)",
    "current_price": {
        "value": "11",
        "currency_code": "USD"
    }
}

4. Delete data in db <br />
DELETE: http://localhost:8081/products/13860428

5. Verify nothing in db <br />
GET: http://localhost:8081/products/

## Documentation:
Swagger documentation of REST services can be found at: http://localhost:8081/swagger-ui/

## ToDo:
JUnit tests
