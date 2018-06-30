package com.clickclackmessenger.ui.custom_view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.clickclackmessenger.R;

import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class OutgoingMessageView extends LinearLayout {
    private TextView timeTextView;
    private TextView nameTextView;
    private TextView messageTextView;
    private CircleImageView imageView;

    public OutgoingMessageView(Context context) {
        this(context, null);
    }

    public OutgoingMessageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOrientation(HORIZONTAL);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        View rootView = inflate(context, R.layout.received_message_view, this);

        timeTextView = rootView.findViewById(R.id.received_message__text_message_time);
        nameTextView = rootView.findViewById(R.id.received_message__text_message_name);
        messageTextView = rootView.findViewById(R.id.received_message__text_message_body);
        imageView = imageView.findViewById(R.id.received_message__image_message_profile);
    }

    public String getTime() {
        return timeTextView.getText().toString();
    }

    public void setTime(String date) {
        timeTextView.setText(date);
    }

    public String getName() {
        return nameTextView.getText().toString();
    }

    public void setName(String name) {
        nameTextView.setText(name);
    }

    public String getMessage() {
        return messageTextView.getText().toString();
    }

    public void setMessage(String message) {
        messageTextView.setText(message);
    }

    public void setTime(Date date) {
        // TODO: 6/26/2018 setdate
    }

    public void setImage(Drawable drawable) {
        imageView.setImageDrawable(drawable);
    }

    public void setImage(int resId) {
        imageView.setImageResource(resId);
    }

    public void setImage(Bitmap bm) {
        imageView.setImageBitmap(bm);
    }

}
