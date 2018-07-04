package com.clickclackmessenger.utils;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import com.clickclackmessenger.ui.chat_screens.Constants;

public class ImageUtils {

    private ImageUtils() {
    }

    public static Drawable getDefaultUserImage(Resources resources, char firstLetter) {
        int vectorId = resources.getIdentifier(Constants.IMAGE_STUB + firstLetter, "drawable", "com.clickclackmessenger");

        return resources.getDrawable(vectorId, null);
    }
}
