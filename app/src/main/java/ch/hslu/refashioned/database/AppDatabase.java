package ch.hslu.refashioned.database;

import androidx.room.*;

import ch.hslu.refashioned.database.dao.BaseDao;
import ch.hslu.refashioned.model.history.Purchase;

@Database(entities = {Purchase.class}, version = 6, exportSchema = false)
@TypeConverters({})
public abstract class AppDatabase extends RoomDatabase {
    public abstract BaseDao<Purchase> clothing();
}
