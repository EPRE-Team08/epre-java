package ch.hslu.refashioned.database.dao;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import ch.hslu.refashioned.model.history.Purchase;

@Dao
public interface ClothingDao extends BaseDao<Purchase> {
    @Override
    @Query("SELECT * FROM Purchase")
    List<Purchase> get();
}
