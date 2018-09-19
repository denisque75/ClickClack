package com.clickclackmessenger.core.entities.users;

public class BaseUser {
    private String name;
    private String lastName;
    private String id;
    private String profileURL;
    private String phoneNumber;

    public BaseUser() {
    }

    public BaseUser(String name, String lastName, String id, String profileURL, String phoneNumber) {
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.id = id;
        this.profileURL = profileURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProfileURL() {
        return profileURL;
    }

    public void setProfileURL(String profileURL) {
        this.profileURL = profileURL;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
