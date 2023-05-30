package ch.hslu.refashioned.ui.history;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import java.util.List;

import ch.hslu.refashioned.model.history.Purchase;
import ch.hslu.refashioned.repository.history.PurchaseRepo;
import ch.hslu.refashioned.service.history.MockPurchaseService;
import ch.hslu.refashioned.service.history.PurchaseService;
import ch.hslu.refashioned.service.history.RoomPurchaseService;

public final class HistoryViewModel extends AndroidViewModel {
    private final PurchaseService service;
    private final List<Purchase> purchases;

    public HistoryViewModel(@NonNull Application application) {
        super(application);

        this.service = new PurchaseRepo(new MockPurchaseService()); //new RoomPurchaseService(application.getApplicationContext));
        this.purchases = this.service.getAll();
    }

    public List<Purchase> get() {
        return purchases;
    }
}