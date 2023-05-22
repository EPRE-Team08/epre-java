package ch.hslu.refashioned.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import ch.hslu.refashioned.database.dao.history.ClothingDao;
import ch.hslu.refashioned.model.history.Clothing;

@Database(entities = {Clothing.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ClothingDao clothingDao();
}
