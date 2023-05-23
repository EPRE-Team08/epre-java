package ch.hslu.refashioned.repository.map;

import java.util.List;

import ch.hslu.refashioned.model.map.Shop;
import ch.hslu.refashioned.service.map.ShopService;

public final class ShopRepo implements ShopService {
    private final ShopService service;

    public ShopRepo(final ShopService service) {
        this.service = service;
    }

    @Override
    public List<Shop> get() {
        return service.get();
    }
}
