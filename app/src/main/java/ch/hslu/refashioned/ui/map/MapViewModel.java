package ch.hslu.refashioned.ui.map;

import androidx.lifecycle.ViewModel;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import ch.hslu.refashioned.model.map.Shop;
import ch.hslu.refashioned.repository.map.ShopRepo;
import ch.hslu.refashioned.service.map.MockShopService;

public final class MapViewModel extends ViewModel {
    private final ShopRepo repo = new ShopRepo(new MockShopService());
    private final List<Shop> shops;

    public MapViewModel() {
        this.shops = repo.get();
    }

    public void populateMap(final GoogleMap map) {
        for (Shop shop : this.shops) {
            MarkerOptions options = new MarkerOptions();
            options.position(shop.getLocation());
            options.title(shop.getName());
            map.addMarker(options);
        }
    }
}
