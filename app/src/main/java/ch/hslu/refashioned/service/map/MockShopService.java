package ch.hslu.refashioned.service.map;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import ch.hslu.refashioned.model.map.Shop;
import ch.hslu.refashioned.model.map.Type;

public final class MockShopService implements ShopService {
    private final List<Shop> items = new ArrayList<>();

    public MockShopService() {
        items.add(new Shop(Type.SHOP, "Shop #1", new LatLng(35, 35)));
        items.add(new Shop(Type.THRIFT, "Thrift Store #1", new LatLng(35, 36)));
        items.add(new Shop(Type.THRIFT, "Thrift Store #2", new LatLng(35, 37)));
    }

    @Override
    public List<Shop> get() {
        return this.items;
    }
}
