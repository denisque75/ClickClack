package com.clickclackmessenger.core.repositories.user_remote_repository;

import com.clickclackmessenger.core.dto.UserName;
import com.clickclackmessenger.core.fb_constants.FBConstantsDB;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class FirebaseUserRepository implements UserRepository {
    private final DatabaseReference database;

    public FirebaseUserRepository(DatabaseReference database) {
        this.database = database;
    }

    @Override
    public void changeName(UserName userName) {
        if (userName.getName() != null && !userName.getName().equals("")) {
            database.child(FBConstantsDB.PATH_USER)
                    .child(FirebaseAuth.getInstance().getUid())
                    .child(FBConstantsDB.USERS.NAME)
                    .setValue(userName.getName());
        }

        if (userName.getLastName() != null && !userName.getLastName().equals("")) {
            database.child(FBConstantsDB.PATH_USER)
                    .child(FirebaseAuth.getInstance().getUid())
                    .child(FBConstantsDB.USERS.LAST_NAME)
                    .setValue(userName.getLastName());
        }
    }
}
