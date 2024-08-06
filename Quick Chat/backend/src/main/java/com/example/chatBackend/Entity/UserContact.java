package com.example.chatBackend.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UserContact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String senderuser;

    private String receiveruser;

    private boolean connected;

    public UserContact() {

    }

    public UserContact(String senderuser, String receiveruser, boolean connected) {
        this.senderuser = senderuser;
        this.receiveruser = receiveruser;
        this.connected = connected;
    }

    public String getSenderuser() {
        return senderuser;
    }

    public void setSenderuser(String senderuser) {
        this.senderuser = senderuser;
    }

    public String getReceiveruser() {
        return receiveruser;
    }

    public void setReceiveruser(String receiveruser) {
        this.receiveruser = receiveruser;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }
}
