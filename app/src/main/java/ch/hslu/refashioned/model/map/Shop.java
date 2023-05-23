package ch.hslu.refashioned.model.map;

import com.google.android.gms.maps.model.LatLng;

import ch.hslu.refashioned.ui.speech.Speakable;

public final class Shop implements Speakable {
    private final Type type;
    private final String name;
    private final String address;
    private final String city;
    private final String country;
    private final LatLng location;

    public Shop(final Type type, final String name, String address, String city, String country, final LatLng location) {
        this.type = type;
        this.name = name;
        this.address = address;
        this.city = city;
        this.country = country;
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

    @Override
    public String getSpeakableText() {
        return this.name + " on " + this.address + " in " + this.city + ", " + this.country;
    }
}
