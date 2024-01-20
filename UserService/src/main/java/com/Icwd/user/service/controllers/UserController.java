package com.Icwd.user.service.controllers;

import com.Icwd.user.service.entities.User;
import com.Icwd.user.service.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);


    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User user1 = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    //Get Single User

    int retryCount=1;

    @GetMapping("/{userId}")
 //   @CircuitBreaker(name="ratingHotelBreaker", fallbackMethod = "ratingHotelFallBackMethod")
    @Retry(name= "ratingHotelService", fallbackMethod = "ratingHotelFallBackMethod")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId){
      //  logger.info("Get Single User Handler: UserController");
        logger.info("Retry count : {}",retryCount);
        retryCount++;
        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }


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

    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){
        List<User> allUser = userService.getAllUser();
        return ResponseEntity.ok(allUser);
    }

}
