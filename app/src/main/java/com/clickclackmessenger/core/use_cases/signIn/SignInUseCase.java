package com.clickclackmessenger.core.use_cases.signIn;

import android.app.Activity;

import com.clickclackmessenger.core.callbak.NetworkCallback;
import com.clickclackmessenger.core.entities.users.BaseUser;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthProvider;

public interface SignInUseCase {

    void verifyPhoneNumber(String phoneNumber, Activity activity, PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks);

    void verifyCode(String verificationId, String code, NetworkCallback<FirebaseUser> callback);

    void saveUser(BaseUser user);

    void removeUser();
}
