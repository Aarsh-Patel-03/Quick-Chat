package com.example.chatBackend.Service;

import com.example.chatBackend.Entity.UserContact;
import com.example.chatBackend.Repository.UserContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserContactService {
    @Autowired
    private UserContactRepository userContactRepository;

    public List<UserContact> getUserContacts(String username) {
        return userContactRepository.findAllBySenderuser(username);
    }

}
