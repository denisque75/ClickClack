package com.clickclackmessenger.ui.login.registration;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

        Button submitButton = rootView.findViewById(R.id.registration__submitButton);
        submitButton.setOnClickListener(this::saveUserName);

        return rootView;
    }

    private void saveUserName(View view) {
        registrationPresenter.registerUser(name.getText().toString(), lastName.getText().toString());
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

    public interface RegistrationCallback extends OpenActivityCallback {

    }
}
