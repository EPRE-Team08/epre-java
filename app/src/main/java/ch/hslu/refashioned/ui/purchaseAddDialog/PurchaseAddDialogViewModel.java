package ch.hslu.refashioned.ui.purchaseAddDialog;

import androidx.lifecycle.ViewModel;

import ch.hslu.refashioned.model.history.ClothingType;
import ch.hslu.refashioned.model.history.PurchaseType;

public class PurchaseAddDialogViewModel extends ViewModel {
    private PurchaseType purchaseType;
    private ClothingType clothingType;

    public void setPurchaseType(PurchaseType purchaseType) {
        this.purchaseType = purchaseType;
    }

    public PurchaseType getPurchaseType() {
        return purchaseType;
    }

    public ClothingType getClothingType() {
        return clothingType;
    }

    public void setClothingType(ClothingType clothingType) {
        this.clothingType = clothingType;
    }
}