package ch.hslu.refashioned.repository.history;

import java.time.LocalDateTime;
import java.util.List;

import ch.hslu.refashioned.model.history.ClothingType;
import ch.hslu.refashioned.model.history.Purchase;
import ch.hslu.refashioned.service.history.PurchaseService;

public final class PurchaseRepo implements PurchaseService {
    private final PurchaseService purchaseService;

    public PurchaseRepo(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @Override
    public void create(Purchase item) {
        purchaseService.create(item);
    }

    @Override
    public void delete(Purchase item) {
        purchaseService.delete(item);
    }

    @Override
    public List<Purchase> getAll() {
        return purchaseService.getAll();
    }

    @Override
    public Purchase getById(LocalDateTime dateTime) {
        return purchaseService.getById(dateTime);
    }

    @Override
    public void update(Purchase item) {
        purchaseService.update(item);
    }

    @Override
    public int countPurchaseBy(LocalDateTime minDate, ClothingType clothingType) {
        return purchaseService.countPurchaseBy(minDate, clothingType);
    }
}
