# Spring-Cloud-Microservices-with-Unit-Testing-and-Integration-Testing

Project Description:  

a) Developed microservices, namely, coupon service, and product service, using Java 8, Spring Cloud, H2 in-memory database, Spring Data JPA, Eureka Server, Feign, and Zuul Proxy Gateway.

b) Implemented CRUD operations, Error Handling, unit tests, and Integration tests for Coupon Service, and Product Service.

c) Added Functionality to enable product micro service make REST call to coupon micro service.

d) Currently, the Coupon Service has REST end points for all CRUD operations, and the product service has REST end point for only POST request.

e) In order to visualize the communication between micro services, run the projects as Spring Boot app in the following order:

1) Run Eureka Server as Spring Boot App.

2) Run Zuul Proxy Gateway as Spring Boot App.

3) Run Coupon Service as Spring Boot App.

4) Run Product Service as Spring boot App.


5) Using Rest Client, create POST request with “http://localhost:8765/coupon-service/couponapi/coupons”, and the JSON data as 
{

    "code": "SUPERSALE1”,
    "discount": 80,
    "expDate": "12/12/2020"
}


6) Using Rest Client, create POST request with “http://localhost:8765/product-service/productapi/products”, and the JSON data as 
{

	"name": "Iphone",
	"description": “iPhone10”,
	"price": 2000,
	"couponCode": "SUPERSALE1"
}

In the aforementioned POST request for products, product service, through Zuul proxy gateway, will communicate with coupon service to get the coupon corresponding to the couponCode in the POST request for products.


7) To access Eureka Naming Server, go to “http://localhost:8761”. Here, you could see the registered services.
