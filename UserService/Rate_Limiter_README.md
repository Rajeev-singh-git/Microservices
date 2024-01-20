# Rate Limiter

# Rate Limiter

Rate Limiter is a traffic cop for microservices. It sets limits on requests to prevent overload, abuse, and ensure smooth, fair, and cost-effective operation.

1. **Preventing Overload:**
    - Avoids overwhelming services with a sudden surge in requests, preventing performance degradation or failure.
2. **Fair Resource Allocation:**
    - Ensures equitable use of shared resources (databases, APIs) among microservices.
3. **DDoS Attack Mitigation:**
    - Helps mitigate the impact of Distributed Denial of Service (DDoS) attacks by controlling request rates.
4. **Throttling and Predictability:**
    - Enables throttling to manage bursts of traffic, maintaining system predictability and stability.
5. **Enhanced Reliability:**
    - Contributes to overall system reliability by preventing services from being overwhelmed.
6. **Improved Quality of Service:**
    - Ensures consistent response times, improving the user experience and system performance.

# Implementation

## Step 1 :→ Add all important dependencies

In `UserService POM.XML`

```xml
<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-actuator</artifactId>
		</dependency>

		<!-- https://mvnrepository.com/artifact/io.github.resilience4j/resilience4j-spring-boot3 -->
<dependency>
			<groupId>io.github.resilience4j</groupId>
			<artifactId>resilience4j-spring-boot3</artifactId>
</dependency>

<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-aop</artifactId>
	</dependency>
```

## Step 2 : → Add annotation

Add annotation on the method which is calling Other Service,

```java
@RateLimiter(name="userRateLimiter", fallbackMethod = "ratingHotelFallBackMethod")
```

```java
@RateLimiter(name="userRateLimiter", fallbackMethod = "ratingHotelFallBackMethod")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId){
        logger.info("Retry count : {}",retryCount);
        retryCount++;
        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }
```

## Step 3→Add Fallback method

Fall back method will be called when rate limit exceeds

```java
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

## Step 4: → Add Configuration

```yaml
resilience4j:
  ratelimiter:
    instances:
      userRateLimiter:
        limit-refresh-period: 4s
        limit-for-period: 2
        timeout-duration: 0s
```

## Step 5:→ Use Jmeter for testing

![Screenshot 2024-01-21 022224](https://github.com/Rajeev-singh-git/Microservices/assets/87664048/15f26909-ef9b-461a-9d5f-1a7e8bf9e9a7)

![Screenshot 2024-01-21 022244](https://github.com/Rajeev-singh-git/Microservices/assets/87664048/9a8434cd-7e2c-469d-88ba-97d401cc30a4)

### Result : →

15 user are hitting request at the same time

For the first 2 user, we are getting result. Because we have set “ `limit-for-period: 2”`   in configuration.

For rest user Fallback method is called

User 1 : →

![Screenshot 2024-01-21 022329](https://github.com/Rajeev-singh-git/Microservices/assets/87664048/e0719782-d3f5-488f-8f92-ff91f6c1ed1c)

User 2 :→

![Screenshot 2024-01-21 022648](https://github.com/Rajeev-singh-git/Microservices/assets/87664048/34d54a3a-1367-462f-8d18-c2961e66bfab)

User 3 :→


For user 3 and therest of the user, fallback method response is coming.

![Screenshot 2024-01-21 022740](https://github.com/Rajeev-singh-git/Microservices/assets/87664048/9516ed48-2bc1-4344-825a-ae663e37aac5)


[Official Documentation](https://resilience4j.readme.io/docs/ratelimiter)
