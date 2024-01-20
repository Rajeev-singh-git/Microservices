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

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/01bbf536-a533-419d-b567-d81390e807ad/2e90fa3b-a4b3-4730-889c-b35a7797bc7e/Untitled.png)

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/01bbf536-a533-419d-b567-d81390e807ad/6f075b2e-0891-4294-bdb0-00e1749ee0c1/Untitled.png)

### Result : →

15 user are hitting request at the same time

For the first 2 user, we are getting result. Because we have set “ `limit-for-period: 2”`   in configuration.

For rest user Fallback method is called

User 1 : →

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/01bbf536-a533-419d-b567-d81390e807ad/7daf4e15-4bd8-4e73-b23e-8a23fd3c3dfd/Untitled.png)

User 2 :→

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/01bbf536-a533-419d-b567-d81390e807ad/56582230-d213-417e-a934-b05ef66b70aa/Untitled.png)

For rest of the user, fallback method response is coming.