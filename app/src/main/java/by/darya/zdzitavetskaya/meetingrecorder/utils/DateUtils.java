package by.darya.zdzitavetskaya.meetingrecorder.utils;

import android.text.format.DateFormat;

import java.util.Date;

public final class DateUtils {

    public static String getFormatDate(final Date date) {
        return DateFormat.format("yyyy-MM-dd HH:mm", date).toString();
    }
}
