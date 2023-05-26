package ch.hslu.refashioned.repository.map;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import ch.hslu.refashioned.model.map.Shop;
import ch.hslu.refashioned.service.map.ShopService;

public final class ShopRepo implements ShopService {
    private final ShopService service;

    public ShopRepo(final ShopService service) {
        this.service = service;
    }

    public List<Shop> getAll() {
        return service.getAll();
    }

    @Override
    public Shop getById(LatLng location) {
        return service.getById(location);
    }
}
