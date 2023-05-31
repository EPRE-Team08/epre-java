package ch.hslu.refashioned.service.history;

import android.content.Context;

import androidx.room.Room;

import java.time.LocalDateTime;
import java.util.List;

import ch.hslu.refashioned.database.AppDatabase;
import ch.hslu.refashioned.database.dao.PurchaseDao;
import ch.hslu.refashioned.model.history.Purchase;
import ch.hslu.refashioned.repository.history.PurchaseRepo;

public final class RoomPurchaseService implements PurchaseService {
    private final PurchaseDao purchaseDao;

    public RoomPurchaseService(Context context) {
        this.purchaseDao = Room.databaseBuilder(context, AppDatabase.class, PurchaseRepo.class.getName())
                .allowMainThreadQueries()
                .build()
                .purchase();
    }


    @Override
    public void create(Purchase item) {
        purchaseDao.insert(item);
    }

    @Override
    public void delete(Purchase item) {
        purchaseDao.delete(item);
    }

    @Override
    public List<Purchase> getAll() {
        return purchaseDao.getAll();
    }

    @Override
    public Purchase getById(LocalDateTime dateTime) {
        return purchaseDao.getById(dateTime);
    }

    @Override
    public void update(Purchase item) {
        purchaseDao.update(item);
    }
}
