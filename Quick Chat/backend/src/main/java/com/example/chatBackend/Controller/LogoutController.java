package com.example.chatBackend.Controller;

import com.example.chatBackend.Service.UserStatusService;
import com.example.chatBackend.Entity.User;
import com.example.chatBackend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")
public class LogoutController {
    private final UserRepository userRepository;
    private final UserStatusService userStatusService;

    @Autowired
    public LogoutController(UserRepository userRepository, UserStatusService userStatusService) {
        this.userRepository = userRepository;
        this.userStatusService = userStatusService;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ResponseEntity<String> logout(@RequestBody String username) {
        User user = userRepository.findByUserName(username);

        if (user != null) {
            // Set user as offline
            userStatusService.setUserOffline(username);

            return new ResponseEntity<>("Logged out successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Logout failed", HttpStatus.UNAUTHORIZED);
        }}
}
