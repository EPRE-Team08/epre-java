package ch.hslu.refashioned.database.dao;

import androidx.room.Dao;
import androidx.room.Query;

import java.time.LocalDateTime;
import java.util.List;

import ch.hslu.refashioned.model.history.Purchase;

@Dao
public interface PurchaseDao extends BaseDao<Purchase> {
    @Query("SELECT * FROM Purchase ORDER BY dateTime DESC")
    List<Purchase> getAll();

    @Query("SELECT * FROM Purchase WHERE dateTime = :dateTime LIMIT 1")
    Purchase getById(LocalDateTime dateTime);
}
