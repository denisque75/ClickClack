package com.clickclackmessenger.ui.inherit_chat;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.clickclackmessenger.core.dto.ConversationUpdate;
import com.clickclackmessenger.core.entities.chats.Conversation;
import com.clickclackmessenger.ui.ProgressBarView;

@StateStrategyType(OneExecutionStateStrategy.class)
public interface InheritChatView extends MvpView, ProgressBarView {

    void showConversation(Conversation conversation);

    void conversationUpdated(ConversationUpdate conversationUpdate);
}
