package com.example.chatBackend.Controller;

import com.example.chatBackend.Entity.Message;
import com.example.chatBackend.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/{senderUsername}/{receiverUsername}")
    public ResponseEntity<List<Message>> getChatMessages(
            @PathVariable String senderUsername,
            @PathVariable String receiverUsername) {
        try {
            List<Message> chatMessages = messageService.getChatMessages(senderUsername, receiverUsername);
            return ResponseEntity.ok(chatMessages);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @PostMapping()
    public ResponseEntity<String> sendMessage(@RequestBody Message message) {
        try {
            // Save the message in the database
            messageService.saveMessage(message);
            return ResponseEntity.ok("Message sent successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error sending message");
        }
    }


    @PutMapping("/{senderUsername}/{messageId}")
    public ResponseEntity<String> updateMessageReadStatus(
            @PathVariable String senderUsername,
            @PathVariable Long messageId) {
        try {
            // Get the message from the database
            Message message = messageService.getMessageById(messageId);
            if (message == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Message not found");
            }

            // Check if the senderUsername matches the sender of the message
            if (!message.getSenderUsername().equals(senderUsername)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized access");
            }

            // Update the readStatus of the message
            message.setReadStatus(true);
            messageService.saveMessage(message); // Save the updated message

            return ResponseEntity.ok("Message read status updated successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating message read status");
        }
    }

}
