package ch.hslu.refashioned.ui.history;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import java.util.List;

import ch.hslu.refashioned.model.history.Purchase;
import ch.hslu.refashioned.repository.history.PurchaseRepo;
import ch.hslu.refashioned.service.history.PurchaseService;
import ch.hslu.refashioned.service.history.RoomPurchaseService;

public final class HistoryViewModel extends ViewModel {
    private final PurchaseService service;
    private final List<Purchase> purchases;

    public HistoryViewModel(final Context context) {
        this.service = new PurchaseRepo(new RoomPurchaseService(context));
        this.purchases = this.service.getAll();
    }

    public List<Purchase> get() {
        return purchases;
    }
}