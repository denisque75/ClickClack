package com.clickclackmessenger.ui.chat_screens;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.clickclackmessenger.core.entities.chats.Chat;
import com.clickclackmessenger.ui.ProgressBarView;

import java.util.List;

@StateStrategyType(OneExecutionStateStrategy.class)
public interface ChatMainScreenView extends MvpView, ProgressBarView {


    void showSearchResults(List<Chat> chats);

    void showDefaultChats();
}
