package com.example.chatBackend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.chatBackend.Entity.UserDetails;
import com.example.chatBackend.Service.UserDetailsService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/users") // Added a common base mapping for all user-related endpoints
public class UserDetailsController {

    @Autowired
    private UserDetailsService userDetailsService;

    @GetMapping("/{userName}")
    public ResponseEntity<UserDetails> getUserDetails(@PathVariable String userName) {
        UserDetails userDetails = userDetailsService.getUserDetailsByUsername(userName);

        if (userDetails != null) {
            return new ResponseEntity<>(userDetails, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/connections/{userName}")
    public ResponseEntity<List<UserDetails>> getAllUsersExceptCurrentUser(@PathVariable String userName) {
        List<UserDetails> allUsersExceptCurrentUser = userDetailsService.getAllUsersExceptCurrentUser(userName);
        return new ResponseEntity<>(allUsersExceptCurrentUser, HttpStatus.OK);
    }

    @PostMapping("/{userName}")
    public ResponseEntity<String> saveUserDetails(@PathVariable String userName, @RequestBody UserDetails userDetails) {
        UserDetails existingUser = userDetailsService.getUserDetailsByUsername(userName);

        if (existingUser != null) {
            existingUser.setNickname(userDetails.getNickname());
            existingUser.setDateOfBirth(userDetails.getDateOfBirth());
            existingUser.setCountry(userDetails.getCountry());
            existingUser.setEmail(userDetails.getEmail());
            existingUser.setAbout(userDetails.getAbout());

            userDetailsService.saveUserDetails(userName,existingUser);
            return new ResponseEntity<>("User details updated successfully", HttpStatus.OK);
        } else {
            userDetails.setUserName(userName);
            userDetailsService.saveUserDetails(userName,userDetails);
            return new ResponseEntity<>("User details saved successfully", HttpStatus.OK);
        }
    }

}


