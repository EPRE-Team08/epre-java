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
    private final int score;

    public Purchase(@NonNull final LocalDateTime dateTime, final String imagePath, final PurchaseType type, final ClothingType clothingType, Brand brand, final int score) {
        this.dateTime = dateTime;
        this.imagePath = imagePath;
        this.type = type;
        this.clothingType = clothingType;
        this.brand = brand;
        this.score = score;
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
        return score;
    }

    public Brand getBrand() {
        return brand;
    }

    /**
     * Two items of the same type allowed per month. Afterwards the score drops.
     *
     * @param purchaseType           The type of the purchase.
     * @param brand                  The brand of the clothing.
     * @param countPurchaseLastMonth The number of purchases of the same type in the last month.
     * @return The score of the purchase.
     */
    public static int calculateScore(PurchaseType purchaseType, Brand brand, int countPurchaseLastMonth) {
        countPurchaseLastMonth = countPurchaseLastMonth > 1 ? countPurchaseLastMonth - 1 : 1;
        float result = purchaseType.getScoreFactor() / countPurchaseLastMonth * brand.getScore().getOverall();
        return Math.round(result);
    }
}
