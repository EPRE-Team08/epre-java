package ch.hslu.refashioned.model.history;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;

@Entity
public final class Purchase {
    @PrimaryKey
    @NonNull
    private final LocalDateTime dateTime;
    private final String imagePath;
    private final PurchaseType type;
    private final ClothingType clothingType;

    public Purchase(@NonNull final LocalDateTime dateTime, final String imagePath, final PurchaseType type, final ClothingType clothingType) {
        this.dateTime = dateTime;
        this.imagePath = imagePath;
        this.type = type;
        this.clothingType = clothingType;
    }

    @NonNull
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getImagePath() {
        return imagePath;
    }

    public PurchaseType getType() {
        return type;
    }

    public ClothingType getClothingType() {
        return clothingType;
    }
}
