package ch.hslu.refashioned.ui.purchaseAddDialog;

import ch.hslu.refashioned.model.history.ClothingType;
import ch.hslu.refashioned.model.history.PurchaseType;

public interface PurchaseAddDialogListener {
    void onDialogPositiveClick(PurchaseType purchaseType, ClothingType clothingType);

    void onDialogNegativeClick();
}
