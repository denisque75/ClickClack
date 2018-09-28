package com.clickclackmessenger.core.entities.chats;

import com.clickclackmessenger.core.entities.users.Interlocutor;

public class Message {
    private Interlocutor sender;
    private String senderId;
    private String message;
    private long createdAt;

    public Message() {
    }

    public Message(String senderId, String message, long createdAt) {
        this.senderId = senderId;
        this.message = message;
        this.createdAt = createdAt;
    }

    public Message(String senderId, Interlocutor sender, String message, long createdAt) {
        this.senderId = senderId;
        this.sender = sender;
        this.message = message;
        this.createdAt = createdAt;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public Interlocutor getSender() {
        return sender;
    }

    public void setSender(Interlocutor sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }


}
