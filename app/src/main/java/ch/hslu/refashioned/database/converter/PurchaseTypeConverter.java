package ch.hslu.refashioned.database.converter;

import androidx.room.TypeConverter;

import java.util.stream.Stream;

import ch.hslu.refashioned.model.history.PurchaseType;

public class PurchaseTypeConverter {
    @TypeConverter
    public static PurchaseType to(final int value) {
        return Stream.of(PurchaseType.values())
                .filter(v -> v.getValue() == value)
                .findFirst()
                .orElse(PurchaseType.FIRST_HAND);
    }

    @TypeConverter
    public static int from(final PurchaseType purchaseType) {
        return purchaseType.getValue();
    }
}
