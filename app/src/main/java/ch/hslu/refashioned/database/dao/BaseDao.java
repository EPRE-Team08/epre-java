package ch.hslu.refashioned.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BaseDao<T> {
    String test();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(T item);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<T> item);

    @Delete
    void delete(T item);

    @Update
    void update(T item);

    @Query("SELECT * FROM ")
    List<T> get();

}
