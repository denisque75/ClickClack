package com.clickclackmessenger.entities.users;

public class BaseUser {
    private String name;
    private String id;
    private String profileURL;

    public BaseUser() {
    }

    public BaseUser(String name, String id, String profileURL) {
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

    public String getProfileURL() {
        return profileURL;
    }

    public void setProfileURL(String profileURL) {
        this.profileURL = profileURL;
    }
}
