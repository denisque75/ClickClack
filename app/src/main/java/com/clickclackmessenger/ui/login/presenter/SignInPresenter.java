package com.clickclackmessenger.ui.login.presenter;

import android.app.Activity;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.clickclackmessenger.core.callbak.NetworkCallback;
import com.clickclackmessenger.core.entities.users.BaseUser;
import com.clickclackmessenger.core.use_cases.signIn.SignInUseCase;
import com.clickclackmessenger.ui.login.SignInView;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

@InjectViewState
public class SignInPresenter extends MvpPresenter<SignInView> {
    private static final String TAG = "SignInPresenter";
    private final SignInUseCase signInUseCase;
    private String verificationId;

    public SignInPresenter(SignInUseCase signInUseCase) {
        this.signInUseCase = signInUseCase;
    }

    public void verifyPhoneStateNumber(String phoneNumber, Activity activity) {
        PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                signInUseCase.saveUser(new BaseUser("", "", "", "", phoneNumber));
                Log.d(TAG, "onVerificationCompleted: ");
                getViewState().successEnterance(null);
                getViewState().hideProgressBar();
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                getViewState().hideProgressBar();
            }

            @Override
            public void onCodeSent(String verificationId, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                SignInPresenter.this.verificationId = verificationId;
                Log.d(TAG, "onCodeSent: ");
                signInUseCase.saveUser(new BaseUser("", "", "", "", phoneNumber));
                getViewState().showCodeField();
            }
        };
        signInUseCase.verifyPhoneNumber(phoneNumber, activity, callbacks);
        getViewState().showProgressbar();
    }

    public void sendCode(String code) {
        signInUseCase.verifyCode(verificationId, code, new NetworkCallback<FirebaseUser>() {
            @Override
            public void onSuccess(FirebaseUser firebaseUser) {
                getViewState().successEnterance(firebaseUser);
            }

            @Override
            public void onFailure(Exception ex) {
                if (ex instanceof FirebaseAuthInvalidCredentialsException) {
                    getViewState().invalidVerificationCode();
                }
            }
        });
    }

    public boolean isDeviceAuthorized() {
        return FirebaseAuth.getInstance().getCurrentUser() != null;
    }
}
