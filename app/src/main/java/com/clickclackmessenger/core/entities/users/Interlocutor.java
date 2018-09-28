package com.clickclackmessenger.core.entities.users;

import android.os.Parcel;
import android.os.Parcelable;

public class Interlocutor extends BaseUser implements Parcelable {

    public Interlocutor() {
    }

    public Interlocutor(String id, String name, String lastName, String profileUrl, String phoneNumber) {
        super(name, lastName, id, profileUrl, phoneNumber);
    }

    public static final Creator<Interlocutor> CREATOR = new Creator<Interlocutor>() {
        @Override
        public Interlocutor createFromParcel(Parcel in) {
            return new Interlocutor(in);
        }

        @Override
        public Interlocutor[] newArray(int size) {
            return new Interlocutor[size];
        }
    };

    protected Interlocutor(Parcel in) {
        super(in);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
