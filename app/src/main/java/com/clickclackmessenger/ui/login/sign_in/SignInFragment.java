package com.clickclackmessenger.ui.login.sign_in;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.clickclackmessenger.R;
import com.clickclackmessenger.core.repositories.db_repository.remote_db.FirebaseSignInDBRepository;
import com.clickclackmessenger.core.repositories.db_repository.remote_db.SignInDBRepository;
import com.clickclackmessenger.core.repositories.db_repository.shared_pref.SharedPrefRepository;
import com.clickclackmessenger.core.repositories.db_repository.shared_pref.UserSharedPrefRepository;
import com.clickclackmessenger.core.repositories.sign_in.SignInRepository;
import com.clickclackmessenger.core.repositories.sign_in.SignInToFirebaseRepository;
import com.clickclackmessenger.core.use_cases.signIn.ClickClackSignInUseCase;
import com.clickclackmessenger.core.use_cases.signIn.SignInUseCase;
import com.clickclackmessenger.ui.MainActivity;
import com.clickclackmessenger.ui.login.OpenActivityCallback;
import com.clickclackmessenger.ui.login.sign_in.presenter.SignInPresenter;
import com.clickclackmessenger.ui.login.text_formatter.CodeListener;
import com.clickclackmessenger.ui.login.text_formatter.CountryCodeTextFormatter;
import com.clickclackmessenger.ui.login.text_formatter.PhoneTextFormatter;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignInFragment extends MvpAppCompatFragment implements SignInView {
    public static final String FRAGMENT_TAG = "signInFragment";
    private static final String TAG = "SignInFragment";
    @InjectPresenter
    SignInPresenter signInPresenter;
    private SignInCallback signInCallback;
    private String phoneNumber = "";
    private String countryCode = "";
    private EditText codeEditText;
    private EditText numberEditText;
    private EditText countryCodeEditText;
    private ProgressBar progressBar;
    private View cellphoneContainer;

    public static SignInFragment newInstance() {
        return new SignInFragment();
    }

    public SignInFragment() {
        // Required empty public constructor
    }

    @ProvidePresenter
    public SignInPresenter provideSignInPresenter() {
        SharedPrefRepository sharedPrefRepository = new UserSharedPrefRepository(getActivity().getSharedPreferences(UserSharedPrefRepository.SHARED_PREF_NAME, MODE_PRIVATE));
        SignInDBRepository signInDBRepository = new FirebaseSignInDBRepository(FirebaseDatabase.getInstance().getReference(), sharedPrefRepository);
        SignInRepository signInRepository = new SignInToFirebaseRepository(signInDBRepository);
        SignInUseCase signInUseCase = new ClickClackSignInUseCase(signInRepository, sharedPrefRepository);
        signInPresenter = new SignInPresenter(signInUseCase);
        return signInPresenter;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sign_in, container, false);

        countryCodeEditText = rootView.findViewById(R.id.cellphone__country_code);
        countryCodeEditText.addTextChangedListener(new CountryCodeTextFormatter(this::numberDialed, this::changeFocus));

        numberEditText = rootView.findViewById(R.id.cellphone__phone_number);
        numberEditText.addTextChangedListener(new PhoneTextFormatter(this::numberDialed));

        codeEditText = rootView.findViewById(R.id.cellphone__code);
        codeEditText.addTextChangedListener(new CodeListener(this::onCodeFilled));

        progressBar = rootView.findViewById(R.id.cellphone__progress_bar);

        cellphoneContainer = rootView.findViewById(R.id.cellphone__phone_container);

        return rootView;
    }

    private void onCodeFilled(String code) {
        signInPresenter.sendCode(code, this::isNewUser);
    }

    private void isNewUser(boolean isNewUser) {
        if (isNewUser) {
            signInCallback.openRegistrationFragment();
        } else {
            signInCallback.openActivity(MainActivity.class);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof SignInCallback) {
            signInCallback = (SignInCallback) context;
        } else {
            throw new IllegalArgumentException("Parent must inherit SignInCallback");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        signInCallback = null;
    }

    private void changeFocus() {
        Log.d(TAG, "changeFocus: ");
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
            signInPresenter.verifyPhoneStateNumber(countryCode + phoneNumber, getActivity());
        }
    }

    @Override
    public void showProgressbar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showCodeField() {
        cellphoneContainer.setVisibility(View.GONE);

        codeEditText.setVisibility(View.VISIBLE);
    }

    @Override
    public void successLogin(FirebaseUser firebaseUser) {

    }

    @Override
    public void invalidVerificationCode() {
        Toast.makeText(getActivity(), "Verification code is invalid", Toast.LENGTH_SHORT).show();
    }

    public interface SignInCallback extends OpenActivityCallback {

        void openRegistrationFragment();
    }
}
