package com.example.chatBackend.Service;

import com.example.chatBackend.Entity.UserContact;
import com.example.chatBackend.Entity.UserDetails;
import com.example.chatBackend.Entity.UserStatus;
import com.example.chatBackend.Repository.UserContactRepository;
import com.example.chatBackend.Repository.UserStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserStatusService {

    @Autowired
    private UserStatusRepository userStatusRepository;


//    public List<UserContact> getUserStatus(String username) {
//        return userStatusRepository.findAllBySenderuser(username);
//    }
    public void setUserOnline(String username) {
        UserStatus userStatus = userStatusRepository.findByUsername(username);
        if (userStatus == null) {
            // Create a new entry for the user
            userStatus = new UserStatus();
            userStatus.setUsername(username);
        }
        userStatus.setOnline(true);
        userStatusRepository.save(userStatus);
    }

    public void setUserOffline(String username) {
        UserStatus userStatus = userStatusRepository.findByUsername(username);
        if (userStatus != null) {
            userStatus.setOnline(false);
            userStatusRepository.save(userStatus);
        }
    }

    public boolean isUserOnline(String username) {
        UserStatus userStatus = userStatusRepository.findByUsername(username);
        return userStatus != null && userStatus.isOnline();
}
    public List<UserStatus> getAllUserStatus() {
        return userStatusRepository.findAll();
    }


}
