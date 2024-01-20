# Fault Tolerance

# What is Fault Tolerance?

It refers to the ability of a microservices architecture to continue functioning and providing service even when some of its individual services encounter errors or failures. This resilience is crucial for ensuring the overall stability and availability of the system.

Here's how fault tolerance translates in the microservices world:

**Challenges:**

- **Distributed nature:** With loosely coupled services, a failure in one can potentially cascade and impact others, bringing down the entire system.
- **Increased complexity:** Managing dependencies and monitoring health across numerous services adds complexity.

**Key principles of fault tolerance in microservices:**

- **Isolation:** Design services to be independent and self-contained, minimizing the impact of a failure on other services.
- **Redundancy:** Implement redundancy where critical, using techniques like service replication or resource pooling.
- **Timeouts:** Set timeouts for service calls to prevent hanging and cascading failures.
- **Circuit Breakers:** Block calls to failing services temporarily to prevent overloading and allow recovery.
- **Retry Mechanisms:** Retry failed calls after a short delay to account for transient issues.
- **Fallback Strategies:** Provide alternative responses or functionalities in case of service unavailability.
- **Asynchronous Communication:** Decouple services by using asynchronous communication to avoid blocking calls.
- **Monitoring and logging:** Continuously monitor service health and log errors for easier troubleshooting and recovery.

**Benefits of implementing fault tolerance:**

- **Increased availability and uptime:** Minimizes service disruptions and guarantees consistent service delivery.
- **Improved robustness:** Makes the system resistant to failures and unexpected errors.
- **Faster recovery:** Ensures quick recovery from failures without cascading effects.
- **Enhanced user experience:** Prevents downtime and maintains user satisfaction.

**Tools and libraries:**

- Spring Cloud: Offers various tools like Hystrix and Resilience4j for implementing fault tolerance patterns.
- Netflix OSS: Provides libraries like Zuul and Ribbon for service discovery and fault tolerance capabilities.
- Other libraries like Feign and Axon Server also simplify fault tolerance mechanisms for microservices.

**Remember:** Designing for fault tolerance from the ground up is key in microservices architecture. By incorporating these principles and tools, you can build resilient and reliable systems that can gracefully handle unexpected challenges.

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/01bbf536-a533-419d-b567-d81390e807ad/e3a5bd91-94a3-4fd7-bab0-27797274e459/Untitled.png)

# Circuit Breaker pattern

It is commonly used in microservices architectures to manage interactions between services and prevent cascading failures.

1. **Closed State (Service is Open):**
  - In this state, requests are allowed to flow from **`HotelService`** to **`UserService`**.
  - Requests are monitored, and if a certain threshold of failures is reached, the circuit breaker transitions to the "Open" state.
2. **Open State (Service is Closed):**
  - In this state, requests from **`HotelService`** to **`UserService`** are blocked, and no communication is allowed.
  - This state is set to prevent unnecessary requests when the dependent service is known to be down.
  - After a certain time, the circuit breaker transitions to the "Half-Open" state to check if the **`UserService`** has recovered.
3. **Half-Open State (Semi-down):**
  - In this state, a limited number of requests are allowed to pass through to **`UserService`** to test its availability.
  - If a certain percentage of these test requests are successful, the circuit breaker transitions back to the "Closed" state (Service is Open).
  - If the test requests continue to fail, the circuit breaker transitions back to the "Open" state.

# Implementing Circuit Breaker using Resilience4J

[Official Documentation :] (https://resilience4j.readme.io/docs/circuitbreaker)

## Step 1 : Add dependency

```xml
<!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-actuator -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-actuator</artifactId>
		</dependency>

		<!-- https://mvnrepository.com/artifact/io.github.resilience4j/resilience4j-spring-boot3 -->
		<dependency>
			<groupId>io.github.resilience4j</groupId>
			<artifactId>resilience4j-spring-boot3</artifactId>
		</dependency>
```

## Step 2: Add `CircuitBreaker` annotation  the method which is calling other services.

```java
@CircuitBreaker(name="ratingHotelBreaker", fallbackMethod = "ratingHotelFallbackMethod")
```

```java
@GetMapping("/{userId}")
    @CircuitBreaker(name="ratingHotelBreaker", fallbackMethod = "ratingHotelFallBackMethod")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId){
        logger.info("Get Single User Handler: UserController");
        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }
```

## Step 3: Create `FallBack` method

In case, Service is down fallback method will be called.

Return type of fallback  method should be same as main method.

```java
//Creating fall back method for circuitBreaker

    public ResponseEntity<User> ratingHotelFallBackMethod(String userId, Exception ex){
        logger.info("FallBack is executed because service is down : {} ", ex.getMessage());
        User user = User.builder()
                .email("dummyemail@gmail.com")
                .name("Dummy")
                .about("Dummy User is created because some service is down")
                .userId("404")
                .build();
        return new ResponseEntity<>(user,HttpStatus.OK);

    }
```

## Step 5: Add Configuration

Add Configuration in UserService Apploication.yml

```java
management:
  health:
    circuitbreakers:
      enabled: true
  endpoints:
    web:
      exposure:
        include: health
  endpoint:
    health:
      show-details: always

resilience4j:
  circuitbreaker:
    configs:
      default:
        eventConsumerBufferSize: 10
        failureRateThreshold: 50
        minimumNumberOfCalls: 5
        automaticTransitionFromOpenToHalfOpenEnabled: true
        waitDurationInOpenState: 3000
        slidingWindowSize: 10
        slidingWindowType: COUNT_BASED
    instances:
      ratingHotelBreaker:
        baseConfig: default
        registerHealthIndicator: true
```

We Can Check health information of Circuit Breaker  on = [localhost:8080/actuator/health](http://localhost:8080/actuator/health)

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/01bbf536-a533-419d-b567-d81390e807ad/a78f7d54-ad8f-48de-8255-6f81952271f7/Untitled.png)

### Result : →

When Circuit is working fine

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/01bbf536-a533-419d-b567-d81390e807ad/c6ae925f-f194-4940-9345-bf0642f83698/Untitled.png)

When some service is down

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/01bbf536-a533-419d-b567-d81390e807ad/dcf82b34-6ac5-4738-9438-d6a70c1be44c/Untitled.png)

Health Check :→