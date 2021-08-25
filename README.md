# Online_Bus_Reservation_System

# Description


## Demonstrated concepts:
    Microservice architecture
    Service discovery
    API gateway

## Microservice architecture
This project demonstrates how to build a new application using microservices, as opposed to a monolith-first strategy. Since each microservice in the project is a module of a 
single parent project, developers have the advantage of being able to run and develop with each microservice running on their local machine. Adding a new microservice is easy, 
as the discovery microservice will automatically discover new services.

## Service discovery
This project contains one discovery service i.e Netflix Eureka.

## API gateway
Netflix-zuul acts as a api gateway in this project.
Each microservice will coordinate with Eureka to retrieve API routes for the entire cluster. Using this strategy each microservice in a cluster can be load balanced and exposed 
through one API gateway. Each service will automatically discover and route API requests to the service that owns the route. This proxying technique is equally helpful when 
developing user interfaces, as the full API of the platform is available through its own host as a proxy.

## Ports
Application | Port 
--- | --- | 
registration-service 	  | 8100, 8101 ... 
search-service  | 8200, 8201 ...
reservation-service   | 8300, 8301 ...
Spring Cloud Config Server 		| 8888
Netflix Eureka Naming Server  | 8761
Netflix Zuul API Gateway Server  | 8765
