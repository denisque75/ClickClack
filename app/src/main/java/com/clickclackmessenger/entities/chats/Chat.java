package com.clickclackmessenger.entities.chats;

public class Chat {
    private String name;
    private String formattedTime;
    private String message;
    private String photoUrl;

    public Chat() {
    }

    public Chat(String name, String message, String formattedTime, String photoUrl) {
        this.name = name;
        this.formattedTime = formattedTime;
        this.message = message;
        this.photoUrl = photoUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
