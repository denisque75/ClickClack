package com.clickclackmessenger.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.clickclackmessenger.R;
import com.clickclackmessenger.ui.chat_screens.ChatMainScreenFragment;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new NavigationItemSelectedListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextMessage = findViewById(R.id.message);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private class NavigationItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_chats:
                    getSupportFragmentManager().beginTransaction().replace(R.id.activity_main__fragment_container, ChatMainScreenFragment.newInstance()).commit();
                    return true;
                case R.id.navigation_contacts:
                    //mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_settings:
                    //mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }

        private void openFragment() {

        }
    }
}
