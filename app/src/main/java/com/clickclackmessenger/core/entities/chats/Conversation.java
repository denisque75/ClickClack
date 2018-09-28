package com.clickclackmessenger.core.entities.chats;

import com.clickclackmessenger.core.entities.users.Member;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Conversation {
    private String chatId;
    private String displayedMessage;
    private long lastMessageTime;

    private List<Member> members;
    private List<Message> messages;

    public Conversation() {
        members = new ArrayList<>();
        messages = new ArrayList<>();
    }

    public Conversation(String chatId, String displayedMessage, long lastMessageTime, List<Member> members, List<Message> messages) {
        this.chatId = chatId;
        this.displayedMessage = displayedMessage;
        this.lastMessageTime = lastMessageTime;
        if (members == null) {
            this.members = new ArrayList<>();
        }
        if (messages == null) {
            this.messages = new ArrayList<>();
        }
    }

    public String getDisplayedMessage() {
        return displayedMessage;
    }

    public void setDisplayedMessage(String displayedMessage) {
        this.displayedMessage = displayedMessage;
    }

    public long getLastMessageTime() {
        return lastMessageTime;
    }

    public void setLastMessageTime(long lastMessageTime) {
        this.lastMessageTime = lastMessageTime;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public void addMember(Member member) {
        if (!members.contains(member)) {
            members.add(member);
        }
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conversation that = (Conversation) o;
        return lastMessageTime == that.lastMessageTime &&
                Objects.equals(chatId, that.chatId) &&
                Objects.equals(displayedMessage, that.displayedMessage) &&
                Objects.equals(members, that.members) &&
                Objects.equals(messages, that.messages);
    }

    @Override
    public int hashCode() {

        return Objects.hash(chatId, displayedMessage, lastMessageTime, members, messages);
    }


}
