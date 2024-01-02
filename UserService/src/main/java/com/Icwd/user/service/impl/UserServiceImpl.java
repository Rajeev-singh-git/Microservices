package com.Icwd.user.service.impl;


import com.Icwd.user.service.entities.Hotel;
import com.Icwd.user.service.entities.Rating;
import com.Icwd.user.service.entities.User;
import com.Icwd.user.service.exceptions.ResourceNotFoundException;
import com.Icwd.user.service.external.services.HotelService;
import com.Icwd.user.service.repositories.UserRepository;
import com.Icwd.user.service.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;


import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestClient client;

    @Autowired
    private HotelService hotelService;


    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }



    @Override
    public User getUser(String userId) {

        //get user from the db with the help of user Repo
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + userId));
        // fetch Rating of the above user from Rating Service

        ParameterizedTypeReference<List<Rating>> typeRef = new ParameterizedTypeReference<List<Rating>>() {};
     //   List<Rating> ratingsOfUser = client.get().uri("http://RATING-SERVICE/ratings/user/" +user.getUserId())
        List<Rating> ratingsOfUser = client.get().uri("http://localhost:8083/ratings/user/" +user.getUserId())
                .retrieve()
                .body(typeRef);

        // for each rating fetch Hotel

         List<Rating> ratingList = ratingsOfUser.stream().map(rating -> {
             //api call to get hotel service
             //Hotel hotels =  client.get().uri("http://HOTEL-SERVICE/hotels/"+rating.getHotelId())
             /* Hotel hotel =  client.get().uri("http://localhost:8081/hotels/"+rating.getHotelId())
               .retrieve()
               .body(new ParameterizedTypeReference<Hotel>() {});  */
             Hotel hotel = hotelService.getHotel(rating.getHotelId());
          //set the hotel to the rating
           rating.setHotel(hotel);
          // return the rating
           return rating;

        }).collect(Collectors.toList());


        user.setRatings(ratingList);
        logger.info("{} ",ratingsOfUser);
        return user;
    }


}
