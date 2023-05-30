package ch.hslu.refashioned.ui.purchases;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.util.List;

import ch.hslu.refashioned.model.history.Purchase;
import ch.hslu.refashioned.repository.history.PurchaseRepo;
import ch.hslu.refashioned.service.history.MockPurchaseService;
import ch.hslu.refashioned.service.history.PurchaseService;

public final class PurchasesViewModel extends AndroidViewModel {
    private final PurchaseService service;
    private final List<Purchase> purchases;

    public PurchasesViewModel(@NonNull Application application) {
        super(application);

        this.service = new PurchaseRepo(new MockPurchaseService()); //new RoomPurchaseService(application.getApplicationContext));
        this.purchases = this.service.getAll();
    }

    public List<Purchase> get() {
        return purchases;
    }
}