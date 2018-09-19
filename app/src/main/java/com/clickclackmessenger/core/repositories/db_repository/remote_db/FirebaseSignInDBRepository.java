package com.clickclackmessenger.core.repositories.db_repository.remote_db;

import android.support.annotation.NonNull;

import com.clickclackmessenger.core.entities.users.BaseUser;
import com.clickclackmessenger.core.repositories.db_repository.shared_pref.SharedPrefRepository;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class FirebaseSignInDBRepository implements SignInDBRepository {
    private final DatabaseReference database;
    private final SharedPrefRepository sharedPrefRepository;

    public FirebaseSignInDBRepository(DatabaseReference database, SharedPrefRepository sharedPrefRepository) {
        this.database = database;
        this.sharedPrefRepository = sharedPrefRepository;
    }

    @Override
    public void saveUserToDB() {
        Query getUserQuery = database.child("users").child(FirebaseAuth.getInstance().getUid());

        BaseUser user = sharedPrefRepository.readUser();

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                BaseUser baseUser = dataSnapshot.getValue(BaseUser.class);
                if (baseUser == null) {
                    dataSnapshot.getRef().child("phoneNumber").setValue(user.getPhoneNumber());
                    dataSnapshot.getRef().child("name").setValue(user.getName());
                    dataSnapshot.getRef().child("lastName").setValue(user.getLastName());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        getUserQuery.addListenerForSingleValueEvent(valueEventListener);
    }
}
