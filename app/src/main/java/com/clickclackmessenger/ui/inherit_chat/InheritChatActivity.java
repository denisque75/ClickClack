package com.clickclackmessenger.ui.inherit_chat;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.clickclackmessenger.App;
import com.clickclackmessenger.R;
import com.clickclackmessenger.core.di.components.InheritChatComponent;
import com.clickclackmessenger.core.dto.ConversationUpdate;
import com.clickclackmessenger.core.entities.chats.Conversation;
import com.clickclackmessenger.core.entities.chats.Message;
import com.clickclackmessenger.core.entities.users.Interlocutor;
import com.clickclackmessenger.core.entities.users.Member;
import com.clickclackmessenger.ui.MainActivity;
import com.clickclackmessenger.ui.inherit_chat.presenter.InheritChatPresenter;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;

import javax.inject.Inject;

public class InheritChatActivity extends MvpAppCompatActivity implements InheritChatView {

    private EditText sendingEditText;
    private ImageView sendButton;
    private RecyclerView recyclerView;
    private MessageAdapter adapter;
    @Inject
    @InjectPresenter
    InheritChatPresenter presenter;
    private ProgressBar progressBar;
    private Interlocutor interlocutor;
    private Conversation conversation;

    @ProvidePresenter
    public InheritChatPresenter providesInheritPresenter() {
        App.injector().plus(new InheritChatComponent.Module()).inject(this);
        return presenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inherit_chat);

        interlocutor = (Interlocutor) getIntent().getParcelableExtra(MainActivity.USER_ID);

        setUpToolbar();

        setUpRecyclerView();

        adapter = new MessageAdapter(FirebaseAuth.getInstance().getUid());
        recyclerView.setAdapter(adapter);
        progressBar = findViewById(R.id.inherit_chat__progress_bar);
        sendButton = findViewById(R.id.button_chatbox_send);
        sendButton.setOnClickListener(this::sendData);

        sendingEditText = findViewById(R.id.edittext_chatbox);

        presenter.findChat(FirebaseAuth.getInstance().getUid(), interlocutor.getId());
        /*try {
            Drawable background = Drawable.createFromStream(getAssets().open("wallpappers/walpapper.png"), null);

            View chatContainer = findViewById(R.id.inherit_chat__container);
            chatContainer.setBackground(background);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    private void sendData(View view) {
        String stringMessage = sendingEditText.getText().toString();

        ConversationUpdate conversationUpdate = new ConversationUpdate();
        conversationUpdate.displayedMessage = stringMessage;
        conversationUpdate.lastMessageTime = Calendar.getInstance().getTimeInMillis();
        conversationUpdate.member = new Member(interlocutor.getId(), true);
        conversationUpdate.message = new Message(FirebaseAuth.getInstance().getUid(), stringMessage, Calendar.getInstance().getTimeInMillis());
        conversationUpdate.chatId = conversation.getChatId();

        presenter.sendMessage(FirebaseAuth.getInstance().getUid(), conversationUpdate);

        conversation.setDisplayedMessage(stringMessage);
        conversation.setLastMessageTime(Calendar.getInstance().getTimeInMillis());
        conversation.addMessage(conversationUpdate.message);

        adapter.addMessage(conversationUpdate.message);
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

    private void setUpToolbar() {
        Toolbar toolbar = findViewById(R.id.inherit_chat__toolbar);

        TextView toolbarNameTextView = findViewById(R.id.inherit_chat__toolbar_username);
        toolbarNameTextView.setText(interlocutor.getName());

        TextView textImage = findViewById(R.id.inherit_chat__textImage);
        String initials = interlocutor.getName().substring(0, 1) + interlocutor.getLastName().substring(0, 1);
        textImage.setText(initials);

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

    @Override
    public void showConversation(Conversation conversation) {
        this.conversation = conversation;
        adapter.setConversation(conversation);
    }

    @Override
    public void conversationUpdated(ConversationUpdate conversationUpdate) {
        if (conversation.getChatId() == null) {
            conversation.setChatId(conversationUpdate.chatId);
        }

        if (conversation.getMembers().size() < 2) {
            conversation.addMember(conversationUpdate.member);
            conversation.addMember(new Member(FirebaseAuth.getInstance().getUid(), true));
        }
    }

    @Override
    public void showProgress(boolean show) {
        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}
