package com.clickclackmessenger.ui.login.presenter;

import android.app.Activity;

public abstract class LoginPresenter {

    public abstract void verifyPhoneStateNumber(String phoneNumber, Activity activity);

    public abstract void sendCode(String code);

}
