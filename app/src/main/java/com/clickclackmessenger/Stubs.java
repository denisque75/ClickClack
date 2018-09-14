package com.clickclackmessenger;

import com.clickclackmessenger.core.entities.chats.Chat;
import com.clickclackmessenger.core.entities.chats.Message;
import com.clickclackmessenger.core.entities.users.BaseUser;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Stubs {

    private Stubs(){}

    public static List<Chat> getChats(){
        List<Chat> chatList = new ArrayList<>();

        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.ENGLISH);

        Chat c1 = new Chat(new BaseUser("Jhon", "Jhonson", "14", ""), "Hello, what are you doing? Do you want to go for a walk?", dateFormat.format(new Date()), "");
        chatList.add(c1);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, -1);
        Date date = calendar.getTime();
        Chat c11 = new Chat(new BaseUser("Mike", "Hunter", "9", ""), "Hi bro! What is the weather in your city tomorrow? I came from France today, it's the best country ever! I was in Paris, and it was beautiful, I love this country and city!!!", dateFormat.format(date), "");
        chatList.add(c11);

        calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, -1);
        date = calendar.getTime();
        Chat c2 = new Chat(new BaseUser("Alex", "Hunter", "12", ""), "Hi Alex! What is the weather in your city tomorrow?", dateFormat.format(date), "");
        chatList.add(c2);

        calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, -1);
        calendar.add(Calendar.MINUTE, -30);
        date = calendar.getTime();
        Chat c3 = new Chat(new BaseUser("Anna", "Boan", "2", ""), "Why do you think so?", dateFormat.format(date), "");
        chatList.add(c3);

        calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, -2);
        date = calendar.getTime();
        Chat c4 = new Chat(new BaseUser("Dario", "De Luca", "22", ""), "OMG!!! hahahahha, it's so funny)", dateFormat.format(date), "");
        chatList.add(c4);

        calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, -2);
        calendar.add(Calendar.MINUTE, -19);
        date = calendar.getTime();
        Chat c5 = new Chat(new BaseUser("Francel", "Duke", "33", ""), "WHAT IS IT??? Can u say him your opinion?", dateFormat.format(date), "");
        chatList.add(c5);

        calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, -2);
        calendar.add(Calendar.MINUTE, -20);
        date = calendar.getTime();
        Chat c6 = new Chat(new BaseUser("Zak", "Mars", "44", ""), "Nice pic bro!", dateFormat.format(date), "");
        chatList.add(c6);

        calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, -2);
        calendar.add(Calendar.MINUTE, -30);
        date = calendar.getTime();
        Chat c7 = new Chat(new BaseUser("Mark", "Nell", "55", ""), "Bro, I bought new car! Do u want to see it?)", dateFormat.format(date), "");
        chatList.add(c7);

        calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, -2);
        calendar.add(Calendar.MINUTE, -55);
        date = calendar.getTime();
        Chat c8 = new Chat(new BaseUser("Victor", "Newman", "123", ""), "Wow! It's so cool! I've never seen the same yet!", dateFormat.format(date), "");
        chatList.add(c8);

        calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, -3);
        calendar.add(Calendar.MINUTE, -1);
        date = calendar.getTime();
        Chat c9 = new Chat(new BaseUser("Kim", "Chon", "442", ""), "I'm so angry!!! Why does my neighbor is sing loud?", dateFormat.format(date), "");
        chatList.add(c9);

        calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, -4);
        calendar.add(Calendar.MINUTE, -44);
        date = calendar.getTime();
        Chat c10 = new Chat(new BaseUser("Elvis", "Prislensky", "512", ""), "Hey man, maybe we need to acquaintance?", dateFormat.format(date), "");
        chatList.add(c10);

        return chatList;
    }

    public static List<Message> getConversation() {
        List<Message> messages = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, -2);
        calendar.add(Calendar.MINUTE, -21);
        Date date = calendar.getTime();
        Message message = new Message(new BaseUser("Denys", "Telezhenko", "owner", ""), "Hi, what's up?", date.getTime());
        messages.add(message);

        calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, -2);
        calendar.add(Calendar.MINUTE, -15);
        date = calendar.getTime();
        message = new Message(new BaseUser("Jhonny", "Jhonson", "12", ""), "Hello, it's ok! How r u?", date.getTime());
        messages.add(message);

        calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, -2);
        calendar.add(Calendar.MINUTE, -12);
        date = calendar.getTime();
        message = new Message(new BaseUser("Denys", "Telezhenko", "owner", ""), "Me 2! Do u have a work?", date.getTime());
        messages.add(message);

        calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, -2);
        calendar.add(Calendar.MINUTE, -9);
        date = calendar.getTime();
        message = new Message(new BaseUser("Jhonny", "Jhonson", "12", ""), "Yeah, sure, I'm a programmer, a software engineer!", date.getTime());
        messages.add(message);

        calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, -1);
        calendar.add(Calendar.MINUTE, -55);
        date = calendar.getTime();
        message = new Message(new BaseUser("Denys", "Telezhenko", "owner", ""), "Wow, cool, me too) Do u beck-end developer? or you are front-end dev?", date.getTime());
        messages.add(message);

        calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, -1);
        calendar.add(Calendar.MINUTE, -33);
        date = calendar.getTime();
        message = new Message(new BaseUser("Jhonny", "Jhonson", "12", ""), "No, I'm the Android developer", date.getTime());
        messages.add(message);


        date = new Date();
        message = new Message(new BaseUser("Denys", "Telezhenko", "owner", ""), "That's cool! I am android dev too!", date.getTime());
        messages.add(message);

        return messages;
    }
}
