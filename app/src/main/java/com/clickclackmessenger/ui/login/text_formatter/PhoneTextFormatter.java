package com.clickclackmessenger.ui.login.text_formatter;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;

import com.clickclackmessenger.ui.login.OnNumberDialed;

public class PhoneTextFormatter implements TextWatcher {
    private static final char SPACE = ' ';
    private static final String TAG = "PhoneTextFormatter";
    private final OnNumberDialed onNumberDialed;

    public PhoneTextFormatter(OnNumberDialed onNumberDialed) {
        this.onNumberDialed = onNumberDialed;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        Log.d(TAG, "beforeTextChanged: ");
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        Log.d(TAG, "onTextChanged: ");
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.length() <= 0) {
            return;
        }

        Log.d(TAG, "afterTextChanged: s.length " + s.length());
        if (s.length() == 12) {
            onNumberDialed.numberDialed(s.toString().replace(" ", ""));
        }
        boolean dashCondition = (s.length() == 3 || s.length() == 7 || s.length() == 10);

        final char c = s.charAt(s.length() - 1);

        if (!Character.isDigit(c)) {
            s.delete(s.length() - 1, s.length());
        }

        if (dashCondition && s.charAt(s.length() - 1) == SPACE) {
            s.delete(s.length() - 1, s.length());
        } else if (dashCondition && Character.isDigit(c)) {
            Log.d(TAG, "afterTextChanged: ");
            s.insert(s.length() - 1, String.valueOf(SPACE));
        }
    }
}
