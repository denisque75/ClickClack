package com.clickclackmessenger.ui.chat_screens;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.clickclackmessenger.R;
import com.clickclackmessenger.entities.chats.Chat;
import com.clickclackmessenger.entities.users.Interlocutor;
import com.clickclackmessenger.utils.ImageUtils;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
    private List<Chat> chats;
    private OnChatChosen chatChosen;

    public ChatAdapter(List<Chat> chats, OnChatChosen chatChosen) {
        this.chats = chats;
        this.chatChosen = chatChosen;
    }

    @Override
    public ChatAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
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
        public final CircleImageView imageView;

        public ViewHolder(View v) {
            super(v);
            name = v.findViewById(R.id.chat__user_name);
            message = v.findViewById(R.id.chat__message);
            time = v.findViewById(R.id.chat__time);
            imageView = v.findViewById(R.id.chat__profile_image);

        }

        private void setChatChosenListener(OnChatChosen chatChosen) {
            itemView.setOnClickListener(v -> {
                chatChosen.chatChosen(new Interlocutor("a1", name.getText().toString()));
            });
        }

        void bindItem(Chat chat) {
            name.setText(chat.getName());
            message.setText(chat.getMessage());
            time.setText(chat.getFormattedTime());
            char firstLetter = chat.getName().toLowerCase().charAt(0);

            imageView.setImageDrawable(ImageUtils.getDefaultUserImage(imageView.getResources(), firstLetter));
        }
    }
}
