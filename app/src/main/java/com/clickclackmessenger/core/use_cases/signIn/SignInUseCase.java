package com.clickclackmessenger.core.use_cases.signIn;

import android.app.Activity;

import com.clickclackmessenger.core.callbacks.NetworkCallback;
import com.clickclackmessenger.core.callbacks.NewUserCallback;
import com.clickclackmessenger.core.entities.users.BaseUser;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthProvider;

public interface SignInUseCase {

    void verifyPhoneNumber(String phoneNumber, Activity activity, PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks);

    void verifyCode(String verificationId, String code, NewUserCallback userCallback, NetworkCallback<FirebaseUser> callback);

    /**
     * Saves user to (local) db if user is not consist yet. In another way, it makes nothing.
     *
     * @param user which will be saved
     */
    void saveUser(BaseUser user);

    void removeUser();
}
