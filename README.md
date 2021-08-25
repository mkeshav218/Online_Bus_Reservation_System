# Online_Bus_Reservation_System

# Description


## Demonstrated concepts:
    Microservice architecture
    Naming Server
    API gateway

## Microservice architecture
This project demonstrates how to build a new application using microservices, as opposed to a monolith-first strategy. Since each microservice in the project is a module of a 
single parent project, developers have the advantage of being able to run and develop with each microservice running on their local machine. Adding a new microservice is easy, 
as the discovery microservice will automatically discover new services.

## Naming Server
Eureka naming server is a REST-based server that holds information about all client service applications. Each microservice registers itself with the Eureka naming server. The naming server registers the client services with their port numbers and IP addresses. It is also known as <strong>Discovery Server</strong>. It runs on the default port <strong>8761</strong>.All the instances of all microservices will be register with the Eureka naming server. Whenever a new instance of a microservice comes up, it would register itself with the Eureka naming server. The registration of microservice with the naming server is called <strong>Service Registration</strong>.
Whenever a service wants to talk with another service, first it will talk with the Eureka naming server. The naming server provides the instances of other service that are currently running. This process of providing instances to other services is called <strong>Service Discovery</strong>.

## API gateway
Netflix-zuul acts as a api gateway in this project. It handles all the requests and performs the dynamic routing of microservice applications. It works as a front door for all the requests. It is also known as <strong>Edge Server</strong>. Each microservice is coordinating with Eureka to retrieve API routes. Using this strategy each microservice is exposed through one API gateway. It runs on the default port <strong>8765</strong>.

## Ports
Application | Port 
--- | --- | 
registration-service | 8100, 8101 ... 
search-service  | 8200, 8201 ...
reservation-service   | 8300, 8301 ...
Spring Cloud Config Server 	| 8888
Netflix Eureka Naming Server  | 8761
Netflix Zuul API Gateway Server  | 8765

## URLs
Application | URL 
--- | --- | 
Eureka | http://localhost:8761/
Spring Cloud Config Server | http://localhost:8888/default , http://localhost:8888/dev
Netflix Zuul API Gateway Server  | http://localhost:8765/{APPLICATION-NAME}/{EndPoint}

