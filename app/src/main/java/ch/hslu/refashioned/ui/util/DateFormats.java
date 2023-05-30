package ch.hslu.refashioned.ui.util;

import java.time.format.DateTimeFormatter;

public final class DateFormats {
    public static DateTimeFormatter getDefault() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    }
}
