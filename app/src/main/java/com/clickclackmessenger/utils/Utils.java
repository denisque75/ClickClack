package com.clickclackmessenger.utils;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class Utils {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.ENGLISH);

    private Utils() {
    }

    public static String getFormattedDate(long time) {
        return dateFormat.format(time);
    }
}
