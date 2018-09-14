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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.clickclackmessenger.R;
import com.clickclackmessenger.Stubs;
import com.clickclackmessenger.core.entities.chats.Message;
import com.clickclackmessenger.core.entities.users.BaseUser;
import com.clickclackmessenger.core.entities.users.Interlocutor;

import java.io.IOException;

public class InheritChat extends AppCompatActivity {
    public static final String INTERLOCUTOR = "INTERLOCUTOR";

    private EditText sendingEditText;
    private ImageView sendButton;
    private RecyclerView recyclerView;
    private MessageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inherit_chat);

        Interlocutor interlocutor = getIntent().getParcelableExtra(INTERLOCUTOR);

        setUpToolbar(interlocutor.getName());

        setUpRecyclerView();

        adapter = new MessageAdapter(Stubs.getConversation());
        recyclerView.setAdapter(adapter);

        try {
            Drawable background = Drawable.createFromStream(getAssets().open("wallpappers/walpapper.png"), null);

            View chatContainer = findViewById(R.id.inherit_chat__container);
            chatContainer.setBackground(background);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sendButton = findViewById(R.id.button_chatbox_send);
        sendButton.setOnClickListener(this::sendData);

        sendingEditText = findViewById(R.id.edittext_chatbox);
    }

    private void sendData(View view) {
        String stringMessage = sendingEditText.getText().toString();
        adapter.addMessage(new Message(new BaseUser("Denys", "Telezhenko", "owner", ""), stringMessage, System.currentTimeMillis()));
        recyclerView.smoothScrollToPosition(adapter.getMaxPosition());
        sendingEditText.setText("");
    }

    private void setUpRecyclerView() {
        recyclerView = findViewById(R.id.inherit_chat__reyclerview_message_list);
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
        manager.setStackFromEnd(true);
        recyclerView.setLayoutManager(manager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }

    private void setUpToolbar(String interlocutorName) {
        Toolbar toolbar = findViewById(R.id.inherit_chat__toolbar);

        TextView toolbarNameTextView = findViewById(R.id.inherit_chat__toolbar_username);
        toolbarNameTextView.setText(interlocutorName);

        TextView textImage = findViewById(R.id.inherit_chat__textImage);
        textImage.setText(interlocutorName.substring(0, 1));

        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        toolbar.setNavigationOnClickListener(this::backPressed);
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
