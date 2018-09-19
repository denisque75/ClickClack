package com.clickclackmessenger.ui.chat_screens;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.clickclackmessenger.R;
import com.clickclackmessenger.core.entities.chats.Chat;
import com.clickclackmessenger.core.entities.users.Interlocutor;

import java.util.ArrayList;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
    private final List<Chat> chats;
    private final OnChatChosen chatChosen;

    public ChatAdapter(List<Chat> chats, OnChatChosen chatChosen) {
        this.chats = chats;
        this.chatChosen = chatChosen;
    }

    public ChatAdapter(OnChatChosen chatChosen) {
        this.chatChosen = chatChosen;
        this.chats = new ArrayList<>();
    }

    @NonNull
    @Override
    public ChatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.chat_item, parent, false);

        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindItem(chats.get(position));
        holder.setChatChosenListener(chatChosen);

    }

    @Override
    public int getItemCount() {
        return chats.size();
    }

    public interface OnChatChosen {

        void chatChosen(Interlocutor interlocutor);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView name;
        public final TextView message;
        public final TextView time;
        public final TextView textImageView;

        public ViewHolder(View v) {
            super(v);
            name = v.findViewById(R.id.chat__user_name);
            message = v.findViewById(R.id.chat__message);
            time = v.findViewById(R.id.chat__time);
            textImageView = v.findViewById(R.id.chat__profile_image);

        }

        private void setChatChosenListener(OnChatChosen chatChosen) {
            itemView.setOnClickListener(v -> {
                chatChosen.chatChosen(new Interlocutor("a1", name.getText().toString()));
            });
        }

        void bindItem(Chat chat) {
            String username = chat.getBaseUser().getName() + " " + chat.getBaseUser().getLastName();
            name.setText(username);
            message.setText(chat.getMessage());
            time.setText(chat.getFormattedTime());

            String userInitials = chat.getBaseUser().getName().substring(0, 1) + chat.getBaseUser().getLastName().charAt(0);
            textImageView.setText(userInitials);
        }
    }
}
