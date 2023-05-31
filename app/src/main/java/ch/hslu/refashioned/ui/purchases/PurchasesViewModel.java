package ch.hslu.refashioned.ui.purchases;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.util.List;

import ch.hslu.refashioned.model.history.Purchase;
import ch.hslu.refashioned.repository.history.PurchaseRepo;
import ch.hslu.refashioned.service.history.PurchaseService;
import ch.hslu.refashioned.service.history.RoomPurchaseService;

public final class PurchasesViewModel extends AndroidViewModel {
    private final PurchaseService service;

    public PurchasesViewModel(@NonNull Application application) {
        super(application);

        this.service = new PurchaseRepo(new RoomPurchaseService(application));
    }

    public List<Purchase> get() {
        return service.getAll();
    }
}