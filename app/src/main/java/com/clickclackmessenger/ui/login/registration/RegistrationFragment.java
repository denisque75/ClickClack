package com.clickclackmessenger.ui.login.registration;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.clickclackmessenger.App;
import com.clickclackmessenger.R;
import com.clickclackmessenger.core.di.components.RegistrationComponent;
import com.clickclackmessenger.ui.MainActivity;
import com.clickclackmessenger.ui.login.OpenActivityCallback;
import com.clickclackmessenger.ui.login.registration.presenter.RegistrationPresenter;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegistrationFragment extends MvpAppCompatFragment implements RegistrationView {
    public static final String REGISTRATION_FRAGMENT = "registrationFragment";

    @Inject
    @InjectPresenter
    RegistrationPresenter registrationPresenter;

    private EditText name;
    private EditText lastName;
    private RegistrationCallback callback;

    private TextInputLayout nameLayout;
    private TextInputLayout lastNameLayout;

    @ProvidePresenter
    public RegistrationPresenter provideRegistrationPresenter() {
        App.injector()
                .plus(new RegistrationComponent.Module())
                .inject(this);
        return registrationPresenter;
    }

    public RegistrationFragment() {
        // Required empty public constructor
    }

    public static RegistrationFragment newInstance() {
        return new RegistrationFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_first_time_user, container, false);

        name = rootView.findViewById(R.id.registration__name_editText);
        lastName = rootView.findViewById(R.id.registration__lastName_editText);

        name.setOnClickListener(this::removeErrorMessage);
        lastName.setOnClickListener(this::removeErrorMessage);

        nameLayout = rootView.findViewById(R.id.registration__name_inputLayout);
        lastNameLayout = rootView.findViewById(R.id.registration__lastName_inputLayout);

        Button submitButton = rootView.findViewById(R.id.registration__submitButton);
        submitButton.setOnClickListener(this::saveUserName);

        return rootView;
    }

    private void removeErrorMessage(View view) {
        Log.d("asdasdas", "removeErrorMessage: ");
        switch (view.getId()) {
            case R.id.registration__name_editText: {
                nameLayout.setError(null);
                name.setText("");
                break;
            }
            case R.id.registration__lastName_editText: {
                lastNameLayout.setError(null);
                lastName.setText("");
                break;
            }
        }
    }

    private void saveUserName(View view) {
        registrationPresenter.registerUser(name.getText().toString().trim(), lastName.getText().toString().trim());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof RegistrationCallback) {
            callback = (RegistrationCallback) context;
        } else {
            throw new IllegalArgumentException("Parent must inherit RegistrationCallback");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
    }

    @Override
    public void registrationIsFinished() {
        callback.openActivity(MainActivity.class);
    }

    @Override
    public void incorrectName(String message) {
        nameLayout.setError(message);
    }

    @Override
    public void incorrectLastName(String message) {
        lastNameLayout.setError(message);
    }

    public interface RegistrationCallback extends OpenActivityCallback {

    }
}
