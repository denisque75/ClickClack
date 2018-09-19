package com.clickclackmessenger.ui.users_page;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.clickclackmessenger.R;
import com.clickclackmessenger.core.repositories.db_repository.shared_pref.UserSharedPrefRepository;
import com.clickclackmessenger.ui.login.SignInActivity;
import com.google.firebase.auth.FirebaseAuth;


public class UsersPageFragment extends Fragment {

    public UsersPageFragment() {
    }

    public static UsersPageFragment newInstance() {
        return new UsersPageFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_users_page, container, false);
        TextView textView = rootView.findViewById(R.id.users_page__sign_out);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                new UserSharedPrefRepository(getActivity().getSharedPreferences(UserSharedPrefRepository.SHARED_PREF_NAME, Context.MODE_PRIVATE)).removeUser();

                Intent intent = new Intent(getActivity(), SignInActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

            }
        });
        return rootView;
    }

}
