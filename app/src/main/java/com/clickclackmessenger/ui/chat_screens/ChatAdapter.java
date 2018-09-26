package com.clickclackmessenger.ui.chat_screens;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.clickclackmessenger.R;
import com.clickclackmessenger.core.entities.chats.Chat;

import java.util.ArrayList;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
    private final List<Chat> chats;
    private final OnChatChosen chatChosen;
    private final List<Chat> searchedData;

    public ChatAdapter(List<Chat> chats, OnChatChosen chatChosen) {
        this.chats = chats;
        this.chatChosen = chatChosen;
        this.searchedData = new ArrayList<>();
    }

    public ChatAdapter(OnChatChosen chatChosen) {
        this.chatChosen = chatChosen;
        this.chats = new ArrayList<>();
        this.searchedData = new ArrayList<>();
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
        if (searchedData.size() > 0) {
            holder.bindItem(searchedData.get(position));
            holder.setChatChosenListener(chatChosen, searchedData.get(position).getBaseUser().getId());
        } else {
            holder.bindItem(chats.get(position));
            holder.setChatChosenListener(chatChosen, searchedData.get(position).getBaseUser().getId());
        }
    }

    @Override
    public int getItemCount() {
        if (searchedData.size() > 0) {
            return searchedData.size();
        } else {
            return chats.size();
        }
    }

    public void setChats(List<Chat> chats) {
        searchedData.clear();
        searchedData.addAll(chats);
        notifyDataSetChanged();
    }

    public void clearSearchData() {
        if (searchedData.size() > 0) {
            searchedData.clear();
            notifyDataSetChanged();
        }
    }

    public interface OnChatChosen {

        void chatChosen(String id);
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

        private void setChatChosenListener(OnChatChosen chatChosen, String id) {
            itemView.setOnClickListener(v -> chatChosen.chatChosen(id));
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
