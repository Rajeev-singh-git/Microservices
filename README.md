# Microservices

Microservice application, containing different application HotelService, RatingService, UserService 

Have ServiceRegistry and APiGateway as well

All application are made on Spring Boort framework but connected to different DB

## HotelService

HotelService is connected to Postgres SQL
Running on port : 8081


## UserService

HotelService is connected to  MySQL
Running on port : 8082

## RatingService

RatingService is connected to MongoDB.
Running on port : 8083

## ServiceRegistry

Port : 8761

## APiGateway

Port : 8084

## ConfigServer

[Steps to create Config Sever](https://github.com/Rajeev-singh-git/Microservices/blob/main/ConfigServer/README.md#what-is-config-server)


## Fault Tolerance

[Steps to create Fault Tolerance](https://github.com/Rajeev-singh-git/Microservices/blob/main/UserService/Fault_Tolerance_README.md)

## Getting Started

1. Clone the repository.
2. Configure the application properties for database connectivity (`application.properties` or `application.yml`).
3. Build and run the application.
