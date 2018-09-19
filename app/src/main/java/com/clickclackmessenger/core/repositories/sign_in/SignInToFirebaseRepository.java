package com.clickclackmessenger.core.repositories.sign_in;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;

import com.clickclackmessenger.core.callbak.NetworkCallback;
import com.clickclackmessenger.core.repositories.db_repository.remote_db.SignInDBRepository;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class SignInToFirebaseRepository implements SignInRepository {
    public static final int TIMEOUT = 20;

    private final SignInDBRepository dbRepository;

    public SignInToFirebaseRepository(SignInDBRepository dbRepository) {
        this.dbRepository = dbRepository;
    }

    private static final String TAG = "SignInToFirebaseReposit";

    @Override
    public void verifyPhoneNumber(String phoneNumber, Activity activity, PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,
                TIMEOUT,
                TimeUnit.SECONDS,
                activity,
                callbacks);
    }

    @Override
    public void verifyCode(String verificationId, String code, NetworkCallback<FirebaseUser> callback) {
        PhoneAuthCredential authCredential = PhoneAuthProvider.getCredential(verificationId, code);
        FirebaseAuth.getInstance().signInWithCredential(authCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.d(TAG, "onComplete");
                    dbRepository.saveUserToDB();
                    callback.onSuccess(task.getResult().getUser());
                } else {
                    Log.e(TAG, "onFailure: ", task.getException());
                    callback.onFailure(task.getException());
                }
            }
        });
    }
}
