package com.clickclackmessenger.ui.chat_screens;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.clickclackmessenger.App;
import com.clickclackmessenger.R;
import com.clickclackmessenger.core.di.components.MainChatComponent;
import com.clickclackmessenger.core.entities.chats.Chat;
import com.clickclackmessenger.ui.chat_screens.presenter.ChatScreenPresenter;

import java.util.List;

import javax.inject.Inject;

public class ChatMainScreenFragment extends MvpAppCompatFragment implements ChatMainScreenView {
    private static final String TAG = "ChatMainScreenFragment";
    private ChatAdapter.OnChatChosen onChatChosen;
    @Inject
    @InjectPresenter
    ChatScreenPresenter presenter;
    private ProgressBar progressBar;
    private ChatAdapter chatAdapter;

    @ProvidePresenter
    public ChatScreenPresenter presenter() {
        App.injector().plus(new MainChatComponent.Module()).inject(this);
        return presenter;
    }

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
        progressBar = rootView.findViewById(R.id.chat__progress_bar);
        progressBar.setVisibility(View.GONE);
        RecyclerView recyclerView = rootView.findViewById(R.id.chat__recycler_view);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        chatAdapter = new ChatAdapter(onChatChosen);
        recyclerView.setAdapter(chatAdapter);

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

        SearchView searchView = null;
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        }

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                hideKeyBoard();
                presenter.searchData(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.equals("")) {
                    chatAdapter.clearSearchData();
                }
                return false;
            }
        });
    }

    private void hideKeyBoard() {
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = getActivity().getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(getActivity());
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public void showProgress(boolean show) {
        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showSearchResults(List<Chat> chats) {
        chatAdapter.setChats(chats);
    }

    @Override
    public void showDefaultChats() {
        chatAdapter.clearSearchData();
    }
}
