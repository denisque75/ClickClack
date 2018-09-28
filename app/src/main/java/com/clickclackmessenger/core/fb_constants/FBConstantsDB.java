package com.clickclackmessenger.core.fb_constants;

public interface FBConstantsDB {
    String PATH_USER = "users";
    String PATH_CHATS = "chats";

    interface Chats {
        String CHAT_ID = "chatId";

        String DISPLAY_MESSAGE = "displayMessage";
        String LAST_MESSAGE_TIME = "lastMessageTime";

        String PATH_MEMBERS = "members";
        String PATH_MESSAGES = "messages";

        interface Members {
            String UID = "uid";
        }

        interface Messages {
            String MESSAGE_ID = "messageId";

            String CREATED_AT = "createdAt";
            String SENDER_ID = "senderId";
            String TEXT = "text";
        }
    }

    interface Users {

        String PATH_CONVERSATIONS = "conversation";

        String PHONE_NUMBER = "phoneNumber";
        String NAME = "name";
        String LAST_NAME = "lastName";

        interface Conversations {
            String USER_ID = "userId";

            String CHAT_ID = "chatId";
            String unseenCount = "unseenCount";
        }
    }
}
