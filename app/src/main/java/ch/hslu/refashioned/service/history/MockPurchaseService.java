package ch.hslu.refashioned.service.history;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import ch.hslu.refashioned.model.history.Brand;
import ch.hslu.refashioned.model.history.ClothingType;
import ch.hslu.refashioned.model.history.Purchase;
import ch.hslu.refashioned.model.history.PurchaseType;

public final class MockPurchaseService implements PurchaseService {

    private final List<Purchase> purchases = new ArrayList<>();

    public MockPurchaseService() {
        purchases.add(new Purchase(
                LocalDateTime.now(), "", PurchaseType.FIRST_HAND, ClothingType.PANTS, Brand.PUMA, Brand.PUMA.getScore().getOverall()
        ));

        purchases.add(new Purchase(
                LocalDateTime.now(), "", PurchaseType.SECOND_HAND, ClothingType.SHIRT, Brand.PUMA, Brand.PUMA.getScore().getOverall()
        ));
    }

    @Override
    public void create(Purchase item) {
        purchases.add(item);
    }

    @Override
    public void delete(Purchase item) {
        purchases.remove(item);
    }

    @Override
    public List<Purchase> getAll() {
        return purchases;
    }

    @Override
    public Purchase getById(LocalDateTime id) {
        return purchases.stream()
                .filter(p -> p.getDateTime().equals(id))
                .findFirst()
                .get();
    }

    @Override
    public void update(Purchase item) {
        // Forget about it
    }
}
