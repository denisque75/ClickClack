package com.clickclackmessenger.ui.login;

import com.arellomobile.mvp.MvpView;
import com.google.firebase.auth.FirebaseUser;

public interface SignInView extends MvpView {

    void showProgressbar();

    void hideProgressBar();

    void showCodeField();

    void successLogin(FirebaseUser firebaseUser);

    void invalidVerificationCode();
}
