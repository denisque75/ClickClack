package com.clickclackmessenger;

import com.clickclackmessenger.entities.chats.Chat;

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

        Chat c1 = new Chat("Jhon Jhonson", "Hello, what are you doing? Do you want to go for a walk?", dateFormat.format(new Date()), "");
        chatList.add(c1);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, -1);
        Date date = calendar.getTime();
        Chat c11 = new Chat("Mike Hunter", "Hi bro! What is the weather in your city tomorrow? I came from France today, it's the best country ever! I was in Paris, and it was beautiful, I love this country and city!!!", dateFormat.format(date), "");
        chatList.add(c11);

        calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, -1);
        date = calendar.getTime();
        Chat c2 = new Chat("Alex Hunter", "Hi Alex! What is the weather in your city tomorrow?", dateFormat.format(date), "");
        chatList.add(c2);

        calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, -1);
        calendar.add(Calendar.MINUTE, -30);
        date = calendar.getTime();
        Chat c3 = new Chat("Anna Boan", "Why do you think so?", dateFormat.format(date), "");
        chatList.add(c3);

        calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, -2);
        date = calendar.getTime();
        Chat c4 = new Chat("Dario De Luca", "OMG!!! hahahahha, it's so funny)", dateFormat.format(date), "");
        chatList.add(c4);

        calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, -2);
        calendar.add(Calendar.MINUTE, -19);
        date = calendar.getTime();
        Chat c5 = new Chat("Francel Duke", "WHAT IS IT??? Can u say him your opinion?", dateFormat.format(date), "");
        chatList.add(c5);

        calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, -2);
        calendar.add(Calendar.MINUTE, -20);
        date = calendar.getTime();
        Chat c6 = new Chat("Zak Mars", "Nice pic bro!", dateFormat.format(date), "");
        chatList.add(c6);

        calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, -2);
        calendar.add(Calendar.MINUTE, -30);
        date = calendar.getTime();
        Chat c7 = new Chat("Mark Nell", "Bro, I bought new car! Do u want to see it?)", dateFormat.format(date), "");
        chatList.add(c7);

        calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, -2);
        calendar.add(Calendar.MINUTE, -55);
        date = calendar.getTime();
        Chat c8 = new Chat("Victor Newman", "Wow! It's so cool! I've never seen the same yet!", dateFormat.format(date), "");
        chatList.add(c8);

        calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, -3);
        calendar.add(Calendar.MINUTE, -1);
        date = calendar.getTime();
        Chat c9 = new Chat("Kim Chon", "I'm so angry!!! Why does my neighbor is sing loud?", dateFormat.format(date), "");
        chatList.add(c9);

        calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, -4);
        calendar.add(Calendar.MINUTE, -44);
        date = calendar.getTime();
        Chat c10 = new Chat("Elvis Prislensky", "Hey man, maybe we need to acquaintance?", dateFormat.format(date), "");
        chatList.add(c10);

        return chatList;
    }
}
