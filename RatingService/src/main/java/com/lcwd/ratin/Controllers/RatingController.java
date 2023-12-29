package com.lcwd.ratin.Controllers;

import com.lcwd.ratin.Service.RatingService;
import com.lcwd.ratin.entities.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    RatingService ratingService;

    @PostMapping
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating){
        Rating createdRating = this.ratingService.create(rating);
        return new ResponseEntity<>(createdRating,HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Rating>> getRating(@RequestBody Rating rating){
        return ResponseEntity.ok(ratingService.getRating());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable String userId){
        return ResponseEntity.ok(ratingService.getRatingByUserId(userId));
    }

    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingByHotelId(@PathVariable String hotelId){
        return ResponseEntity.ok(ratingService.getRatingByHotelId(hotelId));
    }

}

