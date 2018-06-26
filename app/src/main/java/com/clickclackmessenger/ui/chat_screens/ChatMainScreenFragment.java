package com.clickclackmessenger.ui.chat_screens;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.clickclackmessenger.R;
import com.clickclackmessenger.Stubs;

public class ChatMainScreenFragment extends Fragment {

    public ChatMainScreenFragment() {
    }

    public static ChatMainScreenFragment newInstance() {
        return new ChatMainScreenFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_chat_main_screen, container, false);

        RecyclerView recyclerView = rootView.findViewById(R.id.chat__recycler_view);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(new ChatAdapter(Stubs.getChats()));

        return rootView;
    }

}
