package com.clickclackmessenger.core.repositories.sign_in;

import android.app.Activity;

import com.clickclackmessenger.core.callbacks.NetworkCallback;
import com.clickclackmessenger.core.callbacks.NewUserCallback;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthProvider;

public interface SignInRepository {

    void verifyPhoneNumber(String phoneNumber, Activity activity, PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks);

    void verifyCode(String verificationId, String code, NewUserCallback userCallback, NetworkCallback<FirebaseUser> callback);
}
