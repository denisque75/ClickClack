package com.clickclackmessenger.core.entities.chats;

import java.util.Objects;

public class UsersConversationInfo {

    private String conversationId;
    private int unseenCount;

    public UsersConversationInfo() {
    }

    public UsersConversationInfo(String conversationId, int unseenCount) {
        this.conversationId = conversationId;
        this.unseenCount = unseenCount;
    }

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    public int getUnseenCount() {
        return unseenCount;
    }

    public void setUnseenCount(int unseenCount) {
        this.unseenCount = unseenCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersConversationInfo that = (UsersConversationInfo) o;
        return unseenCount == that.unseenCount &&
                Objects.equals(conversationId, that.conversationId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(conversationId, unseenCount);
    }

    @Override
    public String toString() {
        return "UsersConversationInfo{" +
                "conversationId='" + conversationId + '\'' +
                ", unseenCount=" + unseenCount +
                '}';
    }
}
