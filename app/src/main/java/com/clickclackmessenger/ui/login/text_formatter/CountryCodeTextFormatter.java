package com.clickclackmessenger.ui.login.text_formatter;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import com.clickclackmessenger.ui.login.OnNumberDialed;

public class CountryCodeTextFormatter implements TextWatcher {
    private static final String TAG = "CountryCodeTextFormatte";
    private final OnNumberDialed onNumberDialed;
    private final OnNextEditText onNextEditText;

    public CountryCodeTextFormatter(OnNumberDialed onNumberDialed, OnNextEditText onNextEditText) {
        this.onNumberDialed = onNumberDialed;
        this.onNextEditText = onNextEditText;
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
        switch (s.length()) {
            case 0:
                s.append('+');
                return;
            case 1:
                return;
            case 4:
                onNumberDialed.numberDialed(s.toString());
                onNextEditText.changeEditText();
                return;
        }
        final char c = s.charAt(s.length() - 1);

        if (!Character.isDigit(c)) {
            s.delete(s.length() - 1, s.length());
        }
    }

    public interface OnNextEditText {

        void changeEditText();
    }
}
