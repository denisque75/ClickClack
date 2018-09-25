package com.clickclackmessenger.ui.chat_screens;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.clickclackmessenger.R;

public class ChatMainScreenFragment extends Fragment {
    private ChatAdapter.OnChatChosen onChatChosen;

    public ChatMainScreenFragment() {
    }

    public static ChatMainScreenFragment newInstance() {
        return new ChatMainScreenFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_chat_main_screen, container, false);

        //setUpToolbar(rootView);

        setHasOptionsMenu(true);
        RecyclerView recyclerView = rootView.findViewById(R.id.chat__recycler_view);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(new ChatAdapter(onChatChosen));

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ChatAdapter.OnChatChosen) {
            onChatChosen = (ChatAdapter.OnChatChosen) context;
        } else {
            throw new RuntimeException("Activity must implement ChatAdapter.OnChatChosen");
        }

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.search, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);

        android.support.v7.widget.SearchView searchView = null;
        if (searchItem != null) {
            searchView = (android.support.v7.widget.SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        }
    }

    public void setUpToolbar(View rootView) {
        Toolbar toolbar = new Toolbar(rootView.getContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, R.attr.actionBarSize);
        toolbar.setLayoutParams(layoutParams);
        toolbar.setPopupTheme(R.style.AppTheme);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        LinearLayout linearLayout = new LinearLayout(rootView.getContext());
        LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        linearLayout.setLayoutParams(layoutParams1);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);

        ImageView imageView = new ImageView(rootView.getContext());
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams2.gravity = Gravity.CENTER | Gravity.END;
        imageView.setLayoutParams(layoutParams2);
        imageView.setImageResource(R.drawable.ic_search);

        linearLayout.addView(imageView);

        //View view = LayoutInflater.from(rootView.getContext()).inflate(R.layout.search_toolbar_view, null);
        toolbar.addView(linearLayout);
        ((AppCompatActivity) getActivity()).getDelegate().setSupportActionBar(toolbar);
    }
}
