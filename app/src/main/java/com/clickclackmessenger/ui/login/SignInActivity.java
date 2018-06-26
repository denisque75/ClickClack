package com.clickclackmessenger.ui.login;

import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.clickclackmessenger.R;
import com.clickclackmessenger.ui.MainActivity;
import com.clickclackmessenger.ui.login.text_formatter.CodeListener;
import com.clickclackmessenger.ui.login.text_formatter.CountryCodeTextFormatter;
import com.clickclackmessenger.ui.login.text_formatter.PhoneTextFormatter;

public class SignInActivity extends AppCompatActivity {
    private static final String TAG = "SignInActivity";
    private String phoneNumber = "";
    private String countryCode = "";

    private EditText codeEditText;
    private EditText numberEditText;
    private EditText countryCodeEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        countryCodeEditText = findViewById(R.id.cellphone__country_code);
        countryCodeEditText.addTextChangedListener(new CountryCodeTextFormatter(this::numberDialed, this::changeFocus));

        numberEditText = findViewById(R.id.cellphone__phone_number);
        numberEditText.addTextChangedListener(new PhoneTextFormatter(this::numberDialed));

        codeEditText = findViewById(R.id.cellphone__code);
        codeEditText.addTextChangedListener(new CodeListener(this::onCodeFilled));

    }

    private void onCodeFilled(String code) {
        //todo someLogic
        Toast.makeText(this, code, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void changeFocus() {
        Log.d(TAG, "changeFocus: ");
        EditText numberEditText = findViewById(R.id.cellphone__phone_number);
        numberEditText.requestFocus();
    }

    private void numberDialed(String numbers) {
        if (numbers.length() == 4) {
            countryCode = numbers;
        } else if (numbers.length() == 9) {
            phoneNumber = numbers;
        }
        Log.d(TAG, "numberDialed: numbers" + phoneNumber.length());
        if (!phoneNumber.isEmpty() && !countryCode.isEmpty()) {
            //todo logic
            someLogic();
        }
    }

    private void someLogic() {
        ProgressBar progressBar = findViewById(R.id.cellphone__progress_bar);
        progressBar.setVisibility(View.GONE);
        Toast.makeText(this, countryCode + phoneNumber, Toast.LENGTH_SHORT).show();
        View view = findViewById(R.id.cellphone__phone_container);
        view.setVisibility(View.GONE);

        codeEditText.setVisibility(View.VISIBLE);

    }
}
