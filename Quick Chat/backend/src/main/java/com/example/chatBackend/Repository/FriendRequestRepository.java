package com.example.chatBackend.Repository;

import com.example.chatBackend.Entity.FriendRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {
    List<FriendRequest> findByReceiverUsername(String receiverUsername);
    List<FriendRequest> findBySenderUsername(String senderUsername);
}
