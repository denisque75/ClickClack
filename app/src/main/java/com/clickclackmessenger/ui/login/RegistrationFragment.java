package com.clickclackmessenger.ui.login;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.clickclackmessenger.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegistrationFragment extends Fragment {
    public static final String REGISTRATION_FRAGMENT = "registrationFragment";

    public RegistrationFragment() {
        // Required empty public constructor
    }

    public static RegistrationFragment newInstance() {
        return new RegistrationFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first_time_user, container, false);
    }
}
