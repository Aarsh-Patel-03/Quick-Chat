package com.example.chatBackend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.chatBackend.Entity.UserDetails;
import com.example.chatBackend.Repository.UserDetailsRepository;

import java.util.List;

@Service
public class UserDetailsService {

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    public void saveUserDetails(String userName, UserDetails userDetails) {
        userDetails.setUserName(userName);
        userDetailsRepository.save(userDetails);
    }
    public UserDetails getUserDetailsByUsername(String userName) {
        return userDetailsRepository.findByUserName(userName);
    }

    public List<UserDetails> getAllUsersExceptCurrentUser(String userName) {
        return userDetailsRepository.findAllByUserNameNot(userName);
    }
}

