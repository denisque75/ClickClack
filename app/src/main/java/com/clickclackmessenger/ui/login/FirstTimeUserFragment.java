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
public class FirstTimeUserFragment extends Fragment {


    public FirstTimeUserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first_time_user, container, false);
    }

}
