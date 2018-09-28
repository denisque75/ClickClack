package com.clickclackmessenger.core.use_cases.chats;

import com.clickclackmessenger.core.callbacks.NetworkCallback;
import com.clickclackmessenger.core.dto.ConversationUpdate;
import com.clickclackmessenger.core.entities.chats.Conversation;
import com.clickclackmessenger.core.repositories.chat_repository.ChatRepository;

public class InheritChatUseCase implements ChatUseCase {
    private final ChatRepository chatRepository;

    public InheritChatUseCase(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    @Override
    public void findChat(String uid, String interlocutorId, NetworkCallback<Conversation> callback) {
        chatRepository.findChat(uid, interlocutorId, callback);
    }

    @Override
    public void sendMessage(String uid, ConversationUpdate update, NetworkCallback<ConversationUpdate> callback) {
        chatRepository.sendMessage(uid, update, callback);
    }
}
