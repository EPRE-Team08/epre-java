package ch.hslu.refashioned.database.dao;

import androidx.room.Dao;
import androidx.room.Query;

import java.time.LocalDateTime;
import java.util.List;

@Dao
public interface ScoreDao {
    @Query("SELECT ROUND(sustainabilityFactor * sustainabilityScore,0) FROM Purchase ORDER BY dateTime DESC")
    List<Integer> getAll();

    @Query("SELECT ROUND(sustainabilityFactor * sustainabilityScore,0) FROM Purchase WHERE dateTime = :dateTime LIMIT 1")
    int getById(LocalDateTime dateTime);

    @Query("SELECT ROUND(SUM(sustainabilityFactor * sustainabilityScore),0) FROM Purchase")
    int getTotal();

    @Query("SELECT COUNT(dateTime) FROM Purchase")
    int getCount();
}
