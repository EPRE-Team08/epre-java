package ch.hslu.refashioned.repository.history;

import android.content.Context;

import androidx.room.Room;

import java.util.List;

import ch.hslu.refashioned.database.AppDatabase;
import ch.hslu.refashioned.model.history.Clothing;

public class ClothingRepo {
    private final AppDatabase database;

    public ClothingRepo(Context context) {
        this.database = Room
                .databaseBuilder(context, AppDatabase.class, ClothingRepo.class.getName())
                .build();
    }

    public Clothing getById(int id) {
        return database.clothingDao().getById(id);
    }

    public List<Clothing> getAll() {
        return database.clothingDao().getAll();
    }

    public int getSustainabilityScore(){
        return database.clothingDao().getTotalSustainabilityScore();
    }

    public void insertOrUpdate(Clothing... clothing) {
        database.clothingDao().insertAll(clothing);
    }

    public void delete(Clothing clothing) {
        database.clothingDao().delete(clothing);
    }
}
