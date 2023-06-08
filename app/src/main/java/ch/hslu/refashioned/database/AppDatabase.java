package ch.hslu.refashioned.database;

import androidx.room.*;

import ch.hslu.refashioned.database.converter.*;
import ch.hslu.refashioned.database.dao.*;
import ch.hslu.refashioned.model.history.Purchase;

@Database(entities = {Purchase.class}, version = 7, exportSchema = false)
@TypeConverters({PurchaseTypeConverter.class, ClothingTypeConverter.class, DateTimeConverter.class, BrandConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract PurchaseDao purchase();
    public abstract ScoreDao score();
}
