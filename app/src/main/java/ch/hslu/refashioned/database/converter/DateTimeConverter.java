package ch.hslu.refashioned.database.converter;

import androidx.room.TypeConverter;

import java.time.LocalDateTime;

public final class DateTimeConverter {
    @TypeConverter
    public static LocalDateTime toDate(String dateString) {
        return dateString == null ? null : LocalDateTime.parse(dateString);
    }

    @TypeConverter
    public static String fromDate(final LocalDateTime dateTime) {
        return dateTime == null ? null : dateTime.toString();
    }
}
