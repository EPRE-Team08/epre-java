package ch.hslu.refashioned.service.map;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import ch.hslu.refashioned.model.map.Shop;
import ch.hslu.refashioned.model.map.Type;

public final class MockShopService implements ShopService {
    private final List<Shop> items = new ArrayList<>();

    public MockShopService() {
        items.add(new Shop(Type.THRIFT, "Zürcher Brockenhaus", "Neugasse 11", "Zurich", "Switzerland", new LatLng(47.3815277, 8.5285716)));
        items.add(new Shop(Type.THRIFT, "Heilsarmee Brocki", "Geroldstrasse 29", "Zurich", "Switzerland", new LatLng(47.3851672, 8.5160903)));
        items.add(new Shop(Type.THRIFT, "Arche Brockenhaus", "Hohlstrasse 489", "Zurich", "Switzerland", new LatLng(47.3890095, 8.4923862)));
        items.add(new Shop(Type.SHOP, "CIRCLE – The Sustainable Shop", "Niederdorfstrasse 20", "Zurich", "Switzerland", new LatLng(47.3737023, 8.5413416)));
    }

    @Override
    public List<Shop> get() {
        return this.items;
    }
}
