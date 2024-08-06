package com.example.chatBackend.Service;

import com.example.chatBackend.Entity.FriendRequest;
import com.example.chatBackend.Entity.UserContact;
import com.example.chatBackend.Repository.FriendRequestRepository;
import com.example.chatBackend.Repository.UserContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendRequestService {

    @Autowired
    private FriendRequestRepository friendRequestRepository;

    @Autowired
    private UserContactRepository userContactRepository;

    public List<FriendRequest> getAllFriendRequests(String userName) {
        // Fetch all friend requests where the receiver username matches the provided username
        return friendRequestRepository.findByReceiverUsername(userName);
    }
    public List<FriendRequest> getAllFriendRequestsBySender(String senderUsername) {
        return friendRequestRepository.findBySenderUsername(senderUsername);
    }

    public FriendRequest createFriendRequest(FriendRequest friendRequest) {

        return friendRequestRepository.save(friendRequest);
    }

    public void acceptFriendRequest(Long id) {
        // Retrieve the friend request
        FriendRequest friendRequest = friendRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Friend request not found with id: " + id));

        // Create a user contact entry
        UserContact userContact = new UserContact();
        userContact.setSenderuser(friendRequest.getSenderUsername());
        userContact.setReceiveruser(friendRequest.getReceiverUsername());
        userContact.setConnected(true);

        // Set any additional properties for the user contact
        UserContact userContact2 = new UserContact();
        userContact2.setSenderuser(friendRequest.getReceiverUsername());
        userContact2.setReceiveruser(friendRequest.getSenderUsername());
        userContact2.setConnected(true);

        userContactRepository.save(userContact);
        // Save the user contact entry
        userContactRepository.save(userContact2);

        // Delete the friend request
        friendRequestRepository.delete(friendRequest);
    }

    public void removeFriendRequest(Long id) {
        // Delete the friend request
        friendRequestRepository.deleteById(id);
    }
}
