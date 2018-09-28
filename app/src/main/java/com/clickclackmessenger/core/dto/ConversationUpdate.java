package com.clickclackmessenger.core.dto;

import com.clickclackmessenger.core.entities.chats.Message;
import com.clickclackmessenger.core.entities.users.Member;

public class ConversationUpdate {
    public String displayedMessage;
    public long lastMessageTime;
    public String chatId;

    public Member member;
    public Message message;
}
