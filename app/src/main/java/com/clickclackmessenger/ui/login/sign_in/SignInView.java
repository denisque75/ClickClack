package com.clickclackmessenger.ui.login.sign_in;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.google.firebase.auth.FirebaseUser;

public interface SignInView extends MvpView {

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showProgressbar();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void hideProgressBar();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showCodeField();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void successLogin(FirebaseUser firebaseUser);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void invalidVerificationCode();
}
