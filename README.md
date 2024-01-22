# Microservices

Microservice application, containing different application HotelService, RatingService, UserService.

All application are made on Spring Boort framework but connected to different DataBase.


## HotelService

HotelService is connected to Postgres SQL
Running on port : 8081

Having Two Controller class

Get = http://localhost:8081/hotels = will return all hotels
Post = http://localhost:8081/hotels  = will create hotels

```json
{
    "name" : "Ratan Inn",
    "location" : "Delhi",
    "about" : "Best Hotel"
}
```
Get =
http://localhost:8081/hotels/hotelId
http://localhost:8081/hotels/5506f562-588f-47cb-b514-640bb1f38a3a  = will return single hotel

Get = http://localhost:8081/staff  = will return all Staff
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

## Rate Limiter

[Steps to create Rate Limiter](https://github.com/Rajeev-singh-git/Microservices/blob/main/UserService/Rate_Limiter_README.md)

## Security with OKTA Auth

[Steps to create security with OAuth](https://github.com/Rajeev-singh-git/Microservices/blob/main/ApiGateway/OAuth_README.md)


## Getting Started

1. Clone the repository.
2. Configure the application properties for database connectivity (`application.properties` or `application.yml`).
3. Build and run the application.
