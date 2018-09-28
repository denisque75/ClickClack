package com.clickclackmessenger.core.entities.users;

import java.util.Objects;

public class Member {
    private String uid;
    private boolean inChat;

    public Member() {
    }

    public Member(String uid, boolean inChat) {
        this.uid = uid;
        this.inChat = inChat;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public boolean isInChat() {
        return inChat;
    }

    public void setInChat(boolean inChat) {
        this.inChat = inChat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return inChat == member.inChat &&
                Objects.equals(uid, member.uid);
    }

    @Override
    public int hashCode() {

        return Objects.hash(uid, inChat);
    }

    @Override
    public String toString() {
        return "Member{" +
                "uid='" + uid + '\'' +
                ", inChat=" + inChat +
                '}';
    }
}
