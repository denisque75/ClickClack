package com.clickclackmessenger.core.entities.chats;

import com.clickclackmessenger.core.entities.users.BaseUser;

public class Chat {
    private BaseUser baseUser;
    private String formattedTime;
    private String message;
    private String photoUrl;

    public Chat() {
    }

    public Chat(BaseUser baseUser, String message, String formattedTime, String photoUrl) {
        this.baseUser = baseUser;
        this.formattedTime = formattedTime;
        this.message = message;
        this.photoUrl = photoUrl;
    }

    public BaseUser getBaseUser() {
        return baseUser;
    }

    public void setBaseUser(BaseUser baseUser) {
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
