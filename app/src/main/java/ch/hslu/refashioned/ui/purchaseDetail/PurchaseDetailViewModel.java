package ch.hslu.refashioned.ui.purchaseDetail;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.time.LocalDateTime;

import ch.hslu.refashioned.model.history.Purchase;
import ch.hslu.refashioned.repository.history.PurchaseRepo;
import ch.hslu.refashioned.service.history.PurchaseService;
import ch.hslu.refashioned.service.history.RoomPurchaseService;

public class PurchaseDetailViewModel extends AndroidViewModel {
    private final PurchaseService service;
    private LocalDateTime purchaseDate;

    private Purchase purchase;

    public PurchaseDetailViewModel(@NonNull Application application) {
        super(application);

        this.service = new PurchaseRepo(new RoomPurchaseService(application));
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
        loadPurchase();
    }

    private void loadPurchase() {
        purchase = service.getById(purchaseDate);
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void deletePurchase() {
        service.delete(purchase);
    }
}
