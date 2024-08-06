package com.example.chatBackend.Controller;

import com.example.chatBackend.Entity.UserDetails;
import com.example.chatBackend.Entity.UserStatus;
import com.example.chatBackend.Service.UserStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/user")
public class UserStatusController {
    private UserStatusService userStatusService;
    @Autowired
    public UserStatusController(UserStatusService userStatusService) {
        this.userStatusService = userStatusService;
    }

//    @GetMapping("/status")
//    public ResponseEntity<UserStatus> getAllUserStatus() {
//        UserStatus userStatus = userStatusService.getAllUserStatus();
//            return new ResponseEntity<>(userStatus, HttpStatus.OK);
//
//    }

    @GetMapping("/status")
    public List<UserStatus> getAllUserStatus() {
        return userStatusService.getAllUserStatus();
    }

}
