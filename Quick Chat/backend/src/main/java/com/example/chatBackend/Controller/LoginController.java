package com.example.chatBackend.Controller;


import com.example.chatBackend.Service.UserStatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.chatBackend.Entity.User;
import com.example.chatBackend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);


    private final UserRepository userRepository;
    private final UserStatusService userStatusService;

    @Autowired
    public LoginController(UserRepository userRepository, UserStatusService userStatusService) {
        this.userRepository = userRepository;
        this.userStatusService = userStatusService;
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User credentials) {
        User user = userRepository.findByUserName(credentials.getUserName());

        logger.info(user.getUserName());
        if (user != null && user.getPassword().equals(credentials.getPassword())) {
            // Log successful login attempt
//            System.out.println("User logged in: " + credentials.getUserName());
            userStatusService.setUserOnline(credentials.getUserName());
            return new ResponseEntity<>(credentials.getUserName(), HttpStatus.OK);
        } else {
            // Log failed login attempt
//            System.out.println("Failed login attempt for user: " + credentials.getUsername());
            return new ResponseEntity<>("Incorrect username or password", HttpStatus.UNAUTHORIZED);
 }
}
}
