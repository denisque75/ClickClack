package com.clickclackmessenger.core.repositories.chat_repository;

import android.support.annotation.NonNull;

import com.clickclackmessenger.core.callbacks.NetworkCallback;
import com.clickclackmessenger.core.dto.ConversationUpdate;
import com.clickclackmessenger.core.entities.chats.Conversation;
import com.clickclackmessenger.core.entities.chats.UsersConversationInfo;
import com.clickclackmessenger.core.entities.users.Member;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.clickclackmessenger.core.fb_constants.FBConstantsDB.Chats;
import static com.clickclackmessenger.core.fb_constants.FBConstantsDB.PATH_CHATS;
import static com.clickclackmessenger.core.fb_constants.FBConstantsDB.PATH_USER;
import static com.clickclackmessenger.core.fb_constants.FBConstantsDB.Users;

public class InheritChatRepository implements ChatRepository {
    private final DatabaseReference database;

    public InheritChatRepository(DatabaseReference database) {
        this.database = database;
    }

    @Override
    public void findChat(String uid, String interlocutorId, NetworkCallback<Conversation> callback) {
        Query searchConversationId = database
                .child(PATH_USER)
                .child(uid)
                .child(Users.PATH_CONVERSATIONS)
                .child(interlocutorId);

        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue(UsersConversationInfo.class) != null) {
                    //todo
                } else {
                    Conversation conversation = new Conversation();

                    List<Member> members = new ArrayList<>();
                    members.add(new Member(interlocutorId, true));

                    conversation.setMembers(members);

                    callback.onSuccess(conversation);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        searchConversationId.addListenerForSingleValueEvent(eventListener);
    }

    @Override
    public void sendMessage(String uid, ConversationUpdate update, NetworkCallback<ConversationUpdate> callback) {
        String chatId = update.chatId;

        if (chatId != null && !chatId.equals("")) {
            DatabaseReference convers = database.child(PATH_CHATS).child(chatId);
            updateFB(convers, update, uid);

            callback.onSuccess(update);
        } else {
            DatabaseReference newConvers = database.child(PATH_CHATS).push();
            updateFB(newConvers, update, uid);

            chatId = newConvers.getKey();
            update.chatId = chatId;

            DatabaseReference userConv = database.child(PATH_USER).child(Users.PATH_CONVERSATIONS).child(chatId);
            /*userConv.child(Users.Conversations.CHAT_ID).setValue(chatId);
            userConv.child(Users.Conversations.unseenCount).setValue(0);*/
            update.chatId = chatId;

            callback.onSuccess(update);
        }

        //query = database.child(PATH_USER).child(Users.PATH_CONVERSATIONS).child(chatId).
    }

    private void updateFB(DatabaseReference convers, ConversationUpdate update, String uid) {
        convers.child(Chats.DISPLAY_MESSAGE).setValue(update.displayedMessage);
        convers.child(Chats.LAST_MESSAGE_TIME).setValue(update.lastMessageTime);
        convers.child(Chats.PATH_MEMBERS).setValue(update.member);
        convers.child(Chats.PATH_MEMBERS).setValue(new Member(uid, true));
        convers.child(Chats.PATH_MESSAGES).push().child(Chats.PATH_MESSAGES).setValue(update.message);
    }
}
