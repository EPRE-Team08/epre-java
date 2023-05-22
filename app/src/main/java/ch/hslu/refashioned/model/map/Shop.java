package ch.hslu.refashioned.model.map;

import com.google.android.gms.maps.model.LatLng;

public final class Shop {
    private final Type type;
    private final String name;
    private final LatLng location;

    public Shop(final Type type, final String name, final LatLng location) {
        this.type = type;
        this.name = name;
        this.location = location;
    }

    public Type getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public LatLng getLocation() {
        return location;
    }
}
