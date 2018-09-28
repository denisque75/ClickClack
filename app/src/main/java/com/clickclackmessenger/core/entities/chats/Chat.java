package com.clickclackmessenger.core.entities.chats;

import com.clickclackmessenger.core.entities.users.Interlocutor;

public class Chat {
    private Interlocutor baseUser;
    private String formattedTime;
    private String message;
    private String photoUrl;

    public Chat() {
    }

    public Chat(Interlocutor baseUser, String message, String formattedTime, String photoUrl) {
        this.baseUser = baseUser;
        this.formattedTime = formattedTime;
        this.message = message;
        this.photoUrl = photoUrl;
    }

    public Interlocutor getBaseUser() {
        return baseUser;
    }

    public void setBaseUser(Interlocutor baseUser) {
        this.baseUser = baseUser;
    }

    public String getFormattedTime() {
        return formattedTime;
    }

    public void setFormattedTime(String formattedTime) {
        this.formattedTime = formattedTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
