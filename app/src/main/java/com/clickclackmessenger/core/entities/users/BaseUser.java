package com.clickclackmessenger.core.entities.users;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

public class BaseUser implements Parcelable {
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

    public static final Creator<BaseUser> CREATOR = new Creator<BaseUser>() {
        @Override
        public BaseUser createFromParcel(Parcel in) {
            return new BaseUser(in);
        }

        @Override
        public BaseUser[] newArray(int size) {
            return new BaseUser[size];
        }
    };

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseUser baseUser = (BaseUser) o;
        return Objects.equals(name, baseUser.name) &&
                Objects.equals(lastName, baseUser.lastName) &&
                Objects.equals(id, baseUser.id) &&
                Objects.equals(profileURL, baseUser.profileURL) &&
                Objects.equals(phoneNumber, baseUser.phoneNumber);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, lastName, id, profileURL, phoneNumber);
    }

    @Override
    public String toString() {
        return "BaseUser{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", id='" + id + '\'' +
                ", profileURL='" + profileURL + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }


    protected BaseUser(Parcel in) {
        this.name = in.readString();
        this.lastName = in.readString();
        this.id = in.readString();
        this.profileURL = in.readString();
        this.phoneNumber = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.lastName);
        dest.writeString(this.id);
        dest.writeString(this.profileURL);
        dest.writeString(this.phoneNumber);
    }

}
