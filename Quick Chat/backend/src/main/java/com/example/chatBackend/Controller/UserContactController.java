package com.example.chatBackend.Controller;

import com.example.chatBackend.Entity.UserContact;
import com.example.chatBackend.Entity.UserStatus;
import com.example.chatBackend.Service.UserContactService;
import com.example.chatBackend.Service.UserStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserContactController {

    @Autowired
    private UserContactService userContactService;
    @GetMapping("/user-contacts/{username}")
    public List<UserContact> getUserContacts(@PathVariable String username) {
        return userContactService.getUserContacts(username);
    }



}
