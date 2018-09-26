package com.clickclackmessenger.ui.chat_screens.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.clickclackmessenger.core.callbacks.NetworkCallback;
import com.clickclackmessenger.core.entities.chats.Chat;
import com.clickclackmessenger.core.use_cases.search_use_case.SearchUseCase;
import com.clickclackmessenger.ui.chat_screens.ChatMainScreenView;

import java.util.List;

@InjectViewState
public class ChatScreenPresenter extends MvpPresenter<ChatMainScreenView> {
    private static final String TAG = "ChatScreenPresenter";
    private final SearchUseCase searchUseCase;

    public ChatScreenPresenter(SearchUseCase searchUseCase) {
        this.searchUseCase = searchUseCase;
    }

    public void searchData(String query) {
        getViewState().showProgress(true);
        searchUseCase.searchData(query, new NetworkCallback<List<Chat>>() {
            @Override
            public void onSuccess(List<Chat> interlocutors) {
                Log.d(TAG, "onSuccess: ");
                getViewState().showSearchResults(interlocutors);
                getViewState().showProgress(false);
            }

            @Override
            public void onFailure(Exception ex) {
                Log.e(TAG, "onFailure: ", ex);
                getViewState().showProgress(false);
            }
        });
    }

    public void showDefaultChats() {
        getViewState().showDefaultChats();
    }
}
