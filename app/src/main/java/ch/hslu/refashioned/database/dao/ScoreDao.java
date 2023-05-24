package ch.hslu.refashioned.database.dao;

import androidx.room.Dao;
import androidx.room.Query;

import java.time.LocalDateTime;
import java.util.List;

@Dao
public interface ScoreDao {
    @Query("SELECT score FROM Purchase ORDER BY dateTime DESC")
    List<Integer> getAll();

    @Query("SELECT score FROM Purchase WHERE dateTime = :dateTime LIMIT 1")
    int getById(LocalDateTime dateTime);

    @Query("SELECT SUM(score) FROM Purchase")
    int getTotal();
}
