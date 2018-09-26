package com.clickclackmessenger.core.repositories.search_repository;

import android.support.annotation.NonNull;
import android.util.Log;

import com.clickclackmessenger.core.callbacks.NetworkCallback;
import com.clickclackmessenger.core.entities.chats.Chat;
import com.clickclackmessenger.core.entities.users.Interlocutor;
import com.clickclackmessenger.core.fb_constants.FBConstantsDB;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SearchInFirebaseRepository implements SearchRepository {
    private static final String TAG = "SearchInFirebaseReposit";
    private final DatabaseReference database;

    public SearchInFirebaseRepository(DatabaseReference database) {
        this.database = database;
    }

    @Override
    public void searchData(String query, NetworkCallback<List<Chat>> callback) {
        Query searchUsers = database.child(FBConstantsDB.PATH_USER).orderByChild(FBConstantsDB.USERS.PHONE_NUMBER).equalTo(query);

        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d(TAG, "onDataChange: success");
                List<Chat> chats = new ArrayList<>();
                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    Chat chat = new Chat();
                    chat.setBaseUser(snap.getValue(Interlocutor.class));
                    //set interlocutor id
                    chat.getBaseUser().setId(snap.getKey());
                    chats.add(chat);
                }
                callback.onSuccess(chats);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, "onCancelled:", databaseError.toException());
                callback.onFailure(databaseError.toException());
            }
        };
        searchUsers.addListenerForSingleValueEvent(eventListener);

    }
}
