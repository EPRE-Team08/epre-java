package ch.hslu.refashioned.database;

import android.content.Context;

import androidx.room.Room;

public class AppRoom {
    private static AppDatabase instance;

    public static AppDatabase getInstance(Context context) {
        if (instance != null)
            return instance;

        return instance = Room.databaseBuilder(context, AppDatabase.class, "app-database")
                .fallbackToDestructiveMigration()
                .build();

    }
}
