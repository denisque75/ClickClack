package com.clickclackmessenger.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.clickclackmessenger.R;
import com.clickclackmessenger.core.repositories.sign_in.SignInRepository;
import com.clickclackmessenger.core.repositories.sign_in.SignInToFirebaseRepository;
import com.clickclackmessenger.core.use_cases.signIn.ClickClackSignInUseCase;
import com.clickclackmessenger.core.use_cases.signIn.SignInUseCase;
import com.clickclackmessenger.ui.MainActivity;
import com.clickclackmessenger.ui.login.presenter.SignInPresenter;
import com.clickclackmessenger.ui.login.text_formatter.CodeListener;
import com.clickclackmessenger.ui.login.text_formatter.CountryCodeTextFormatter;
import com.clickclackmessenger.ui.login.text_formatter.PhoneTextFormatter;
import com.google.firebase.auth.FirebaseUser;

public class SignInActivity extends MvpAppCompatActivity implements SignInView {
    private static final String TAG = "SignInActivity";
    private String phoneNumber = "";
    private String countryCode = "";

    private EditText codeEditText;
    private EditText numberEditText;
    private EditText countryCodeEditText;


    @InjectPresenter
    SignInPresenter signInPresenter;

    @ProvidePresenter
    public SignInPresenter provideSignInPresenter() {
        SignInRepository signInRepository = new SignInToFirebaseRepository();
        SignInUseCase signInUseCase = new ClickClackSignInUseCase(signInRepository);
        signInPresenter = new SignInPresenter(signInUseCase);
        return signInPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (signInPresenter.isDeviceAuthorized()) {
            Log.d(TAG, "device authorized");
            startActivity(new Intent(this, MainActivity.class));
        }

        setContentView(R.layout.activity_sign_in);
        countryCodeEditText = findViewById(R.id.cellphone__country_code);
        countryCodeEditText.addTextChangedListener(new CountryCodeTextFormatter(this::numberDialed, this::changeFocus));

        numberEditText = findViewById(R.id.cellphone__phone_number);
        numberEditText.addTextChangedListener(new PhoneTextFormatter(this::numberDialed));

        codeEditText = findViewById(R.id.cellphone__code);
        codeEditText.addTextChangedListener(new CodeListener(this::onCodeFilled));

    }

    private void onCodeFilled(String code) {
        signInPresenter.sendCode(code);

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
            signInPresenter.verifyPhoneStateNumber(countryCode + phoneNumber, this);
        }
    }

    @Override
    public void showProgressbar() {
        ProgressBar progressBar = findViewById(R.id.cellphone__progress_bar);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        ProgressBar progressBar = findViewById(R.id.cellphone__progress_bar);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showCodeField() {
        View view = findViewById(R.id.cellphone__phone_container);
        view.setVisibility(View.GONE);

        codeEditText.setVisibility(View.VISIBLE);
    }

    @Override
    public void successEnterance(FirebaseUser firebaseUser) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void invalidVerificationCode() {
        Toast.makeText(this, "Verification code is invalid", Toast.LENGTH_SHORT).show();
    }
}
