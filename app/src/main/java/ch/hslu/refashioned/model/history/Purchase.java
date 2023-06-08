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
    private final Brand brand;
    private final float sustainabilityFactor;
    private final int sustainabilityScore;

    public Purchase(@NonNull final LocalDateTime dateTime, final String imagePath, final PurchaseType type, final ClothingType clothingType, Brand brand, final int sustainabilityScore, final float sustainabilityFactor) {
        this.dateTime = dateTime;
        this.imagePath = imagePath;
        this.type = type;
        this.clothingType = clothingType;
        this.brand = brand;
        this.sustainabilityFactor = sustainabilityFactor;
        this.sustainabilityScore = sustainabilityScore;
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

    public int getScore() {
        return Math.round(sustainabilityScore * sustainabilityFactor);
    }

    public int getSustainabilityScore() {
        return sustainabilityScore;
    }

    public float getSustainabilityFactor() {
        return sustainabilityFactor;
    }

    public Brand getBrand() {
        return brand;
    }

    /**
     * Two items of the same type allowed per month. Afterwards the score drops.
     *
     * @param purchaseType           The type of the purchase.
     * @param countPurchaseLastMonth The number of purchases of the same type in the last month.
     * @return The sustainability factor of the purchase based on the purchase type and the number of purchases of the same type in the last month.
     */
    public static float calculateSustainabilityFactor(PurchaseType purchaseType, int countPurchaseLastMonth) {
        countPurchaseLastMonth = countPurchaseLastMonth > 1 ? countPurchaseLastMonth - 1 : 1;
        return purchaseType.getScoreFactor() / countPurchaseLastMonth;
    }
}
