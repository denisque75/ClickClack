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
import com.clickclackmessenger.R;
import com.clickclackmessenger.core.repositories.db_repository.shared_pref.SharedPrefRepository;
import com.clickclackmessenger.core.repositories.db_repository.shared_pref.UserSharedPrefRepository;
import com.clickclackmessenger.core.repositories.user_remote_repository.FirebaseUserRepository;
import com.clickclackmessenger.core.repositories.user_remote_repository.UserRepository;
import com.clickclackmessenger.core.use_cases.change_name_use_case.ChangeNameUseCase;
import com.clickclackmessenger.core.use_cases.change_name_use_case.ClickClackUserNameUseCase;
import com.clickclackmessenger.ui.MainActivity;
import com.clickclackmessenger.ui.login.OpenActivityCallback;
import com.clickclackmessenger.ui.login.registration.presenter.RegistrationPresenter;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegistrationFragment extends MvpAppCompatFragment implements RegistrationView {
    public static final String REGISTRATION_FRAGMENT = "registrationFragment";

    @InjectPresenter
    RegistrationPresenter registrationPresenter;
    private EditText name;
    private EditText lastName;
    private RegistrationCallback callback;

    @ProvidePresenter
    public RegistrationPresenter provideRegistrationPresenter() {
        UserRepository userRepository = new FirebaseUserRepository(FirebaseDatabase.getInstance().getReference());
        SharedPrefRepository sharedPrefRepository = new UserSharedPrefRepository(getActivity()
                .getSharedPreferences(UserSharedPrefRepository.SHARED_PREF_NAME, Context.MODE_PRIVATE));
        ChangeNameUseCase changeNameUseCase = new ClickClackUserNameUseCase(sharedPrefRepository, userRepository);
        return new RegistrationPresenter(changeNameUseCase);
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
