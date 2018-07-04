package com.clickclackmessenger.ui.chat_screens;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.clickclackmessenger.R;
import com.clickclackmessenger.entities.chats.Message;
import com.clickclackmessenger.utils.DateUtils;

import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class MessageAdapter extends RecyclerView.Adapter {
    private static final int VIEW_TYPE_MESSAGE_SENT = 1;
    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 2;

    private List<Message> messageList;
    private Map<String, Drawable> avatarMap;

    public MessageAdapter(List<Message> messageList, Map<String, Drawable> avatarMap) {
        this.messageList = messageList;
        this.avatarMap = avatarMap;
    }

    @Override
    public int getItemViewType(int position) {
        String userId = messageList.get(position).getSender().getId();

        if (userId.equals("owner")) {
            return VIEW_TYPE_MESSAGE_SENT;
        } else {
            return VIEW_TYPE_MESSAGE_RECEIVED;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        if (viewType == VIEW_TYPE_MESSAGE_SENT) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sent_message_item, parent, false);
            return new SentViewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.received_message_view, parent, false);
            return new ReceivedViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Message message = messageList.get(position);

        switch (holder.getItemViewType()) {
            case VIEW_TYPE_MESSAGE_SENT:
                ((SentViewHolder) holder).bind(message);
                break;
            case VIEW_TYPE_MESSAGE_RECEIVED:
                ((ReceivedViewHolder) holder).bind(message, avatarMap.get(message.getSender().getId()));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    private static class ReceivedViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView circleImageView;
        private TextView messageTextView;
        private TextView timeTextView;

        ReceivedViewHolder(View itemView) {
            super(itemView);
            circleImageView = itemView.findViewById(R.id.received_message__image_message_profile);
            messageTextView = itemView.findViewById(R.id.received_message__text_message_body);
            timeTextView = itemView.findViewById(R.id.received_message__text_message_time);

        }

        void bind(Message message, Drawable avatar) {
            circleImageView.setImageDrawable(avatar);

            messageTextView.setText(message.getMessage());
            timeTextView.setText(DateUtils.getFormattedDate(message.getCreatedAt()));
        }

    }

    private static class SentViewHolder extends RecyclerView.ViewHolder {
        private TextView messageTextView;
        private TextView timeTextView;

        SentViewHolder(View itemView) {
            super(itemView);
            messageTextView = itemView.findViewById(R.id.sent__text_message_body);
            timeTextView = itemView.findViewById(R.id.sent__text_message_time);
        }

        void bind(Message message) {
            messageTextView.setText(message.getMessage());
            timeTextView.setText(DateUtils.getFormattedDate(message.getCreatedAt()));
        }
    }
}
