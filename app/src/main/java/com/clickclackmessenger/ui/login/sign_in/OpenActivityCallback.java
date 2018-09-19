package com.clickclackmessenger.ui.login.sign_in;

import android.app.Activity;

public interface OpenActivityCallback {

    void openActivity(Class<? extends Activity> activity);
}
