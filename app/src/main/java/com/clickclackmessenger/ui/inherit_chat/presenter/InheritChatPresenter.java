package com.clickclackmessenger.ui.inherit_chat.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.clickclackmessenger.core.callbacks.NetworkCallback;
import com.clickclackmessenger.core.dto.ConversationUpdate;
import com.clickclackmessenger.core.entities.chats.Conversation;
import com.clickclackmessenger.core.use_cases.chats.ChatUseCase;
import com.clickclackmessenger.ui.inherit_chat.InheritChatView;

@InjectViewState
public class InheritChatPresenter extends MvpPresenter<InheritChatView> {
    private final ChatUseCase chatUseCase;

    public InheritChatPresenter(ChatUseCase chatUseCase) {
        this.chatUseCase = chatUseCase;
    }

    public void findChat(String uid, String interlocutorUid) {
        getViewState().showProgress(true);
        chatUseCase.findChat(uid, interlocutorUid, new NetworkCallback<Conversation>() {
            @Override
            public void onSuccess(Conversation conversation) {
                getViewState().showProgress(false);
                getViewState().showConversation(conversation);
            }

            @Override
            public void onFailure(Exception ex) {
                getViewState().showProgress(false);
            }
        });
    }

    public void sendMessage(String uid, ConversationUpdate conversationUpdate) {
        chatUseCase.sendMessage(uid, conversationUpdate, new NetworkCallback<ConversationUpdate>() {
            @Override
            public void onSuccess(ConversationUpdate update) {
                getViewState().conversationUpdated(update);
            }

            @Override
            public void onFailure(Exception ex) {

            }
        });
    }

}
