package com.clickclackmessenger.ui.chat_screens;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.clickclackmessenger.R;
import com.clickclackmessenger.Stubs;
import com.clickclackmessenger.entities.users.Interlocutor;

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
        }

        RecyclerView recyclerView = findViewById(R.id.inherit_chat__reyclerview_message_list);
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
        manager.setStackFromEnd(true);
        recyclerView.setLayoutManager(manager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(new MessageAdapter(Stubs.getConversation()));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
