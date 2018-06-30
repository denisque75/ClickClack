package com.clickclackmessenger.entities.users;

import android.os.Parcel;
import android.os.Parcelable;

public class Interlocutor implements Parcelable {
    public static final Parcelable.Creator<Interlocutor> CREATOR = new Parcelable.Creator<Interlocutor>() {
        @Override
        public Interlocutor createFromParcel(Parcel source) {
            return new Interlocutor(source);
        }

        @Override
        public Interlocutor[] newArray(int size) {
            return new Interlocutor[size];
        }
    };
    private String id;
    private String name;

    public Interlocutor() {
    }

    public Interlocutor(String id, String name) {
        this.id = id;
        this.name = name;
    }

    protected Interlocutor(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
    }
}
