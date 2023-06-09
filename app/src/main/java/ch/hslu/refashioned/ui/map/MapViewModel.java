package ch.hslu.refashioned.ui.map;

import androidx.lifecycle.ViewModel;

import com.google.android.gms.maps.model.Marker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ch.hslu.refashioned.model.map.Shop;
import ch.hslu.refashioned.repository.map.ShopRepo;
import ch.hslu.refashioned.service.map.MockShopService;
import ch.hslu.refashioned.service.map.ShopService;

public final class MapViewModel extends ViewModel {
    private final ShopService service = new ShopRepo(new MockShopService());
    private final List<Shop> shops;
    private final Map<Marker, Shop> shopMap = new HashMap<>();

    public MapViewModel() {
        this.shops = service.getAll();
    }

    public List<Shop> get() {
        return this.shops;
    }

    public Shop get(final Marker marker) {
        return shopMap.get(marker);
    }

    public void put(final Marker marker, final Shop shop) {
        this.shopMap.put(marker, shop);
    }
}
