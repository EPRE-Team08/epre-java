package ch.hslu.refashioned.database.converter;

import androidx.room.TypeConverter;

import java.util.stream.Stream;

import ch.hslu.refashioned.model.history.Brand;

public class BrandConverter {
    @TypeConverter
    public static int to(final Brand brand) {
        return brand.getValue();
    }

    @TypeConverter
    public static Brand from(final int value) {
        return Stream.of(Brand.values())
                .filter(p -> p.getValue() == value)
                .findFirst()
                .orElse(Brand.PUMA);
    }
}
