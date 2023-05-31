package ch.hslu.refashioned.service.history;

import android.content.Context;

import androidx.room.Room;

import java.time.LocalDateTime;
import java.util.List;

import ch.hslu.refashioned.database.AppDatabase;
import ch.hslu.refashioned.database.dao.ScoreDao;
import ch.hslu.refashioned.repository.history.PurchaseRepo;

public final class RoomScoreService implements ScoreService {
    private final ScoreDao scoreDao;

    public RoomScoreService(Context context) {
        this.scoreDao = Room.databaseBuilder(context, AppDatabase.class, PurchaseRepo.class.getName())
                .allowMainThreadQueries()
                .build()
                .score();
    }

    @Override
    public List<Integer> getAll() {
        return scoreDao.getAll();
    }

    @Override
    public Integer getById(LocalDateTime dateTime) {
        return scoreDao.getById(dateTime);
    }

    public int getTotal() {
        return scoreDao.getTotal();
    }
}
