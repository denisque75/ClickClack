package com.clickclackmessenger.core.use_cases.chats;

import com.clickclackmessenger.core.callbacks.NetworkCallback;
import com.clickclackmessenger.core.dto.ConversationUpdate;
import com.clickclackmessenger.core.entities.chats.Conversation;

public interface ChatUseCase {

    void findChat(String uid, String interlocutorId, NetworkCallback<Conversation> callback);

    void sendMessage(String uid, ConversationUpdate update, NetworkCallback<ConversationUpdate> callback);
}
