package com.clickclackmessenger.core.use_cases.signIn;

import android.app.Activity;

import com.clickclackmessenger.core.callbak.NetworkCallback;
import com.clickclackmessenger.core.repositories.sign_in.SignInRepository;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthProvider;

public class ClickClackSignInUseCase implements SignInUseCase {
    private final SignInRepository signInRepository;

    public ClickClackSignInUseCase(SignInRepository signInRepository) {
        this.signInRepository = signInRepository;
    }

    @Override
    public void verifyPhoneNumber(String phoneNumber, Activity activity, PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks) {
        signInRepository.verifyPhoneNumber(phoneNumber, activity, callbacks);
    }

    @Override
    public void verifyCode(String verificationId, String code, NetworkCallback<FirebaseUser> callback) {
        signInRepository.verifyCode(verificationId, code, callback);
    }
}
