package com.clickclackmessenger.core.repositories.db_repository.shared_pref;

import android.content.SharedPreferences;

import com.clickclackmessenger.core.dto.UserName;
import com.clickclackmessenger.core.entities.users.BaseUser;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;

public class UserSharedPrefRepository implements SharedPrefRepository {
    public static final String PHONE_NUMBER = "phone.number";
    public static final String NAME = "user.name";
    public static final String LAST_NAME = "user.last.name";

    public static final String SHARED_PREF_NAME = "user.repository";

    private final SharedPreferences sharedPreferences;

    @Inject
    public UserSharedPrefRepository(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    @Override
    public void addUser(BaseUser baseUser) {
        sharedPreferences
                .edit()
                .putString(PHONE_NUMBER, baseUser.getPhoneNumber())
                .putString(NAME, baseUser.getName())
                .putString(LAST_NAME, baseUser.getLastName())
                .apply();
    }

    @Override
    public void removeUser() {
        sharedPreferences
                .edit()
                .remove(PHONE_NUMBER)
                .remove(NAME)
                .remove(LAST_NAME)
                .apply();
    }

    @Override
    public BaseUser readUser() {
        BaseUser baseUser = new BaseUser();
        baseUser.setId(FirebaseAuth.getInstance().getUid());
        baseUser.setPhoneNumber(sharedPreferences.getString(PHONE_NUMBER, ""));
        baseUser.setName(sharedPreferences.getString(NAME, ""));
        baseUser.setLastName(sharedPreferences.getString(LAST_NAME, ""));
        return baseUser;
    }

    @Override
    public void editUserName(UserName userName) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (userName.getName() != null && !userName.getName().equals("")) {
            editor.putString(NAME, userName.getName());
        }

        if (userName.getLastName() != null && !userName.getLastName().equals("")) {
            editor.putString(LAST_NAME, userName.getLastName());
        }
        editor.apply();
    }
}
