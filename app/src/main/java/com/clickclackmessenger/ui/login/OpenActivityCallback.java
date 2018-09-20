package com.clickclackmessenger.ui.login;

import android.app.Activity;

public interface OpenActivityCallback {

    void openActivity(Class<? extends Activity> activity);
}
