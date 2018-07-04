package com.clickclackmessenger.ui.chat_screens;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.clickclackmessenger.R;
import com.clickclackmessenger.Stubs;
import com.clickclackmessenger.entities.users.Interlocutor;
import com.clickclackmessenger.utils.ImageUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class InheritChat extends AppCompatActivity {
    public static final String INTERLOCUTOR = "INTERLOCUTOR";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inherit_chat);

        Interlocutor interlocutor = getIntent().getParcelableExtra(INTERLOCUTOR);

        Toolbar toolbar = findViewById(R.id.inherit_chat__toolbar);
        TextView toolbarNameTextView = findViewById(R.id.inherit_chat__toolbar_username);
        toolbarNameTextView.setText(interlocutor.getName());
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false
            );
        }

        toolbar.setNavigationOnClickListener(this::backPressed);

        RecyclerView recyclerView = findViewById(R.id.inherit_chat__reyclerview_message_list);
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
        manager.setStackFromEnd(true);
        recyclerView.setLayoutManager(manager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        Map<String, Drawable> avatarMap = new HashMap<>();


        char firstLetter = interlocutor.getName().toLowerCase().charAt(0);
        Drawable ava = ImageUtils.getDefaultUserImage(getResources(), firstLetter);
        avatarMap.put("12", ava);
        recyclerView.setAdapter(new MessageAdapter(Stubs.getConversation(), avatarMap));

        try {
            Drawable background = Drawable.createFromStream(getAssets().open("wallpappers/walpapper.png"), null);

            View chatContainer = findViewById(R.id.inherit_chat__container);
            chatContainer.setBackground(background);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.inherit_chat_menu, menu);
        return true;
    }

    private void backPressed(View view) {
        onBackPressed();
    }

}
