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


# Circuit Breaker pattern

![Screenshot 2024-01-20 175354](https://github.com/Rajeev-singh-git/Microservices/assets/87664048/a2c50424-48ab-4b40-8302-5d01ebfddd46)


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

![Screenshot 2024-01-20 234124](https://github.com/Rajeev-singh-git/Microservices/assets/87664048/909f9ed8-3f29-4177-a966-af0e2c6b840d)


### Result : →

When Circuit is working fine

![Screenshot 2024-01-20 234248](https://github.com/Rajeev-singh-git/Microservices/assets/87664048/fbb2e23e-8b48-4630-9014-95c200109073)

When some service is down

![Screenshot 2024-01-20 234341](https://github.com/Rajeev-singh-git/Microservices/assets/87664048/89ee3e6e-018f-4ccc-8491-67ab0b8e33ed)

Health Check :→

![Screenshot 2024-01-20 234425](https://github.com/Rajeev-singh-git/Microservices/assets/87664048/284d0619-dccc-4494-a842-a10d586cac28)


# Retry

We can use retry if we are not sure if service is down.

## When to use Retry?

**1. Not sure if service is down:** Retry is a valid option when you're unsure if the service is truly down or if the issue is due to a transient problem like a network glitch.

**2. Network glitch:** Highlighting the possibility of network glitches explicitly reinforces the idea that retries can be helpful in such situations.

**3. Slow response:** Slow responses can also be addressed by retries, as the service might be temporarily overloaded or experiencing delays.

**However, it's important to add some caveats:**

- **Idempotent operations:** Only retry for operations that are safe to repeat, meaning they won't cause unintended side effects.
- **Exponential backoff:** Implement retries with exponential backoff to avoid overloading the service with repeated requests.
- **Maximum attempts:** Set a reasonable maximum number of retries to prevent infinite loops.
- **Monitoring:** Track retry rates and logs to understand the nature of failures and adjust strategies accordingly.

## Implementing Retry

## Step 1 :→ Add @Retry annotation

Add @Retry annotation on the method which is calling other Services.

```java
@Retry(name= "ratingHotelService", fallbackMethod = "ratingHotelFallBackMethod")
```

```java
int retryCount=1;

    @GetMapping("/{userId}")
    @Retry(name= "ratingHotelService", fallbackMethod = "ratingHotelFallBackMethod")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId){
        logger.info("Retry count : {}",retryCount);
        retryCount++;
        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }
```

## Step 2 :→ Add fallback method

If after retry still it is not able to get response, Fallback method will be called

## Step 3 :→ Add Configuration in UserService Apploication.yml

```yaml
resilience4j:
  retry:
    instances:
      ratingHotelService:
        max-attempt: 3
        wait-duration: 5s
```

### Result →

If after hitting the URL, it doesn’t get response from the service.

![Screenshot 2024-01-21 004020](https://github.com/Rajeev-singh-git/Microservices/assets/87664048/ef50f40d-17da-40ed-85ac-d6b117fc1de1)

It retries 3 times after 5 second each time, as it is configured. Then Fall Back method is called.


![Screenshot 2024-01-21 004238](https://github.com/Rajeev-singh-git/Microservices/assets/87664048/076a8fe8-2b94-49a5-a76d-7625132fea61)


[Official Documentation](https://resilience4j.readme.io/docs/circuitbreaker)
