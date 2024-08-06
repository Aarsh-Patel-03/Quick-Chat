package com.example.chatBackend.Controller;

import com.example.chatBackend.Entity.FriendRequest;
import com.example.chatBackend.Service.FriendRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/friend-requests")
public class FriendRequestController {

    @Autowired
    private FriendRequestService friendRequestService;


    @GetMapping("/{userName}")
    public List<FriendRequest> getAllFriendRequests(@PathVariable String userName) {
        return friendRequestService.getAllFriendRequests(userName);
    }

    @GetMapping("/sender/{senderUsername}")
    public List<FriendRequest> getAllFriendRequestsBySender(@PathVariable String senderUsername) {
        return friendRequestService.getAllFriendRequestsBySender(senderUsername);
    }

    @PostMapping
    public FriendRequest createFriendRequest(@RequestBody FriendRequest friendRequest) {
        System.out.println(friendRequest.getSenderUsername());
        System.out.println(friendRequest.getReceiverUsername());
        return friendRequestService.createFriendRequest(friendRequest);
    }

    @PostMapping("/{id}/accept")
    public ResponseEntity<?> acceptFriendRequest(@PathVariable("id") Long id) {
        try {
            friendRequestService.acceptFriendRequest(id);
            return ResponseEntity.ok("Friend request accepted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error accepting friend request");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeFriendRequest(@PathVariable("id") Long id) {
        try {
            friendRequestService.removeFriendRequest(id);
            return ResponseEntity.ok("Friend request removed successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error removing friend request");
        }
    }
}
