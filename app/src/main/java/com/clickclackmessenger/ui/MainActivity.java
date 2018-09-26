package com.clickclackmessenger.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.clickclackmessenger.R;
import com.clickclackmessenger.core.entities.users.Interlocutor;
import com.clickclackmessenger.ui.chat_screens.ChatAdapter;
import com.clickclackmessenger.ui.chat_screens.ChatMainScreenFragment;
import com.clickclackmessenger.ui.inherit_chat.InheritChatActivity;
import com.clickclackmessenger.ui.users_page.UsersPageFragment;

public class MainActivity extends AppCompatActivity implements ChatAdapter.OnChatChosen {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new NavigationItemSelectedListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.main_activity__toolbar);
        setSupportActionBar(toolbar);
        mTextMessage = findViewById(R.id.message);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public void chatChosen(Interlocutor interlocutor) {
        Intent intent = new Intent(this, InheritChatActivity.class);
        startActivity(intent);
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return super.onCreateOptionsMenu(menu);
    }*/

    private class NavigationItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_chats:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.activity_main__fragment_container, ChatMainScreenFragment.newInstance())
                            .commit();
                    return true;
                case R.id.navigation_contacts:
                    //mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_settings:
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.activity_main__fragment_container, UsersPageFragment.newInstance())
                            .commit();
                    return true;
            }
            return false;
        }

        private void openFragment() {

        }
    }


}
