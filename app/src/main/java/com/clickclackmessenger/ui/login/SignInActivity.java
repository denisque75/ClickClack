package com.clickclackmessenger.ui.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.clickclackmessenger.R;
import com.clickclackmessenger.ui.MainActivity;
import com.clickclackmessenger.ui.login.registration.RegistrationFragment;
import com.clickclackmessenger.ui.login.sign_in.SignInFragment;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity implements SignInFragment.SignInCallback,
        RegistrationFragment.RegistrationCallback {

    private static final String TAG = "SignInActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            Log.d(TAG, "device authorized");

            openActivity(MainActivity.class);
        }
        setContentView(R.layout.activity_sign_in);

        openSignInFragment();
    }

    private void openSignInFragment() {
        SignInFragment signInFragment = (SignInFragment) getSupportFragmentManager().findFragmentByTag(SignInFragment.FRAGMENT_TAG);
        if (signInFragment == null) {
            signInFragment = SignInFragment.newInstance();
        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.sign_in__container, signInFragment, SignInFragment.FRAGMENT_TAG)
                .commit();
    }

    @Override
    public void openRegistrationFragment() {
        RegistrationFragment registrationFragment = (RegistrationFragment) getSupportFragmentManager().findFragmentByTag(RegistrationFragment.REGISTRATION_FRAGMENT);
        if (registrationFragment == null) {
            registrationFragment = RegistrationFragment.newInstance();
        }
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.sign_in__container, registrationFragment, RegistrationFragment.REGISTRATION_FRAGMENT)
                .commit();
    }

    @Override
    public void openActivity(Class<? extends Activity> activity) {
        Intent intent = new Intent(this, activity);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
