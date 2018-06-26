package com.clickclackmessenger.ui.login.text_formatter;

import android.text.Editable;
import android.text.TextWatcher;

public class CodeListener implements TextWatcher {
    private OnCodeFilled onCodeFilled;

    public CodeListener(OnCodeFilled onCodeFilled) {
        this.onCodeFilled = onCodeFilled;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.length() == 6) {
            onCodeFilled.codeFilled(s.toString());
        }
    }

    public interface OnCodeFilled {

        void codeFilled(String code);
    }
}
