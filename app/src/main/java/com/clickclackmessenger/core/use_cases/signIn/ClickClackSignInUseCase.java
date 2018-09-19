package com.clickclackmessenger.core.use_cases.signIn;

import android.app.Activity;

import com.clickclackmessenger.core.callbacks.NetworkCallback;
import com.clickclackmessenger.core.callbacks.NewUserCallback;
import com.clickclackmessenger.core.entities.users.BaseUser;
import com.clickclackmessenger.core.repositories.db_repository.shared_pref.SharedPrefRepository;
import com.clickclackmessenger.core.repositories.sign_in.SignInRepository;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthProvider;

public class ClickClackSignInUseCase implements SignInUseCase {
    private final SignInRepository signInRepository;
    private final SharedPrefRepository sharedPrefRepository;

    public ClickClackSignInUseCase(SignInRepository signInRepository, SharedPrefRepository sharedPrefRepository) {
        this.signInRepository = signInRepository;
        this.sharedPrefRepository = sharedPrefRepository;
    }

    @Override
    public void verifyPhoneNumber(String phoneNumber, Activity activity, PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks) {
        signInRepository.verifyPhoneNumber(phoneNumber, activity, callbacks);
    }

    @Override
    public void verifyCode(String verificationId, String code, NewUserCallback userCallback, NetworkCallback<FirebaseUser> callback) {
        signInRepository.verifyCode(verificationId, code, userCallback, callback);
    }

    @Override
    public void saveUser(BaseUser user) {
        sharedPrefRepository.addUser(user);
    }

    @Override
    public void removeUser() {
        sharedPrefRepository.removeUser();
    }
}
