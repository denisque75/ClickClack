package com.clickclackmessenger.ui.inherit_chat;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.clickclackmessenger.R;
import com.clickclackmessenger.core.entities.chats.Conversation;
import com.clickclackmessenger.core.entities.chats.Message;
import com.clickclackmessenger.utils.DateUtils;

public class MessageAdapter extends RecyclerView.Adapter {
    private static final int VIEW_TYPE_MESSAGE_SENT = 1;
    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 2;

    private final String ownerUid;
    private Conversation conversation;

    public MessageAdapter(String ownerUid) {
        this.ownerUid = ownerUid;
        conversation = new Conversation();
    }

    public MessageAdapter(String ownerUid, Conversation conversation) {
        this(ownerUid);
        this.conversation = conversation;
    }

    @Override
    public int getItemViewType(int position) {
        String userId = conversation.getMessages().get(position).getSenderId();

        if (userId.equals(ownerUid)) {
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
        Message message = conversation.getMessages().get(position);

        switch (holder.getItemViewType()) {
            case VIEW_TYPE_MESSAGE_SENT:
                ((SentViewHolder) holder).bind(message);
                break;
            case VIEW_TYPE_MESSAGE_RECEIVED:
                ((ReceivedViewHolder) holder).bind(message);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return conversation.getMessages().size();
    }

    public void addMessage(Message message) {
        conversation.getMessages().add(message);
        notifyItemChanged(conversation.getMessages().size() - 1);

    }

    public void setConversation(Conversation conversation) {
        if (conversation != null) {
            this.conversation = conversation;
            notifyDataSetChanged();
        }
    }

    public int getMaxPosition() {
        return conversation.getMessages().size() - 1;
    }

    private static class ReceivedViewHolder extends RecyclerView.ViewHolder {
        private TextView textImageView;
        private TextView messageTextView;
        private TextView timeTextView;

        ReceivedViewHolder(View itemView) {
            super(itemView);
            textImageView = itemView.findViewById(R.id.received_message__image_message_profile);
            messageTextView = itemView.findViewById(R.id.received_message__text_message_body);
            timeTextView = itemView.findViewById(R.id.received_message__text_message_time);

        }

        void bind(Message message) {
            String senderInitials = message.getSender().getName().substring(0, 1) + message.getSender().getLastName().charAt(0);
            textImageView.setText(senderInitials);

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
