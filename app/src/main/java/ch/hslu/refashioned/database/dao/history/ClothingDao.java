package ch.hslu.refashioned.database.dao.history;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import ch.hslu.refashioned.model.history.Clothing;

@Dao
public interface ClothingDao {
    @Query("SELECT * FROM clothing ORDER BY scan_date_time DESC")
    List<Clothing> getAll();

    @Query("SELECT * FROM clothing WHERE id LIKE :id LIMIT 1")
    Clothing getById(int id);

    @Query("SELECT SUM(sustainability_score) AS total FROM clothing")
    int getTotalSustainabilityScore();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Clothing... clothing);

    @Delete
    void delete(Clothing clothing);
}
