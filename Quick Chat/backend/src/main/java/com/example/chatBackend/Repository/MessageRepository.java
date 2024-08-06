package com.example.chatBackend.Repository;

import com.example.chatBackend.Entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    Optional<Message> findById(Long messageId);
    List<Message> findBySenderUsernameAndReceiverUsernameOrSenderUsernameAndReceiverUsernameOrderByTimestamp(
            String senderUsername1, String receiverUsername1, String senderUsername2, String receiverUsername2);
}
