package com.clickclackmessenger.entities.chats;

import com.clickclackmessenger.entities.users.BaseUser;

public class Message {
    private BaseUser sender;
    private String message;
    private long createdAt;

    public Message() {
    }

    public Message(BaseUser sender, String message, long createdAt) {
        this.sender = sender;
        this.message = message;
        this.createdAt = createdAt;
    }

    public BaseUser getSender() {
        return sender;
    }

    public void setSender(BaseUser sender) {
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
