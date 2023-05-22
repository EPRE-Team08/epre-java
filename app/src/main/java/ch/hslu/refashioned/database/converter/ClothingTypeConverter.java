package ch.hslu.refashioned.database.converter;

import androidx.room.TypeConverter;

import java.util.stream.Stream;

import ch.hslu.refashioned.model.history.ClothingType;

public final class ClothingTypeConverter {
    @TypeConverter
    public static ClothingType to(final int value) {
        return Stream.of(ClothingType.values())
                .filter(p -> p.getValue() == value)
                .findFirst()
                .orElse(ClothingType.SHIRT);
    }

    @TypeConverter
    public static int from(final ClothingType purchaseType) {
        return purchaseType.getValue();
    }
}
