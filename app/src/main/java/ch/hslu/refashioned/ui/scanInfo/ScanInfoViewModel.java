package ch.hslu.refashioned.ui.scanInfo;

import android.app.Application;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.time.LocalDateTime;

import ch.hslu.refashioned.model.history.Brand;
import ch.hslu.refashioned.model.history.ClothingType;
import ch.hslu.refashioned.model.history.Purchase;
import ch.hslu.refashioned.service.history.PurchaseService;
import ch.hslu.refashioned.service.history.RoomPurchaseService;

public class ScanInfoViewModel extends AndroidViewModel {
    private Uri imageUri;
    private Brand brand;

    private PurchaseService purchaseService;

    public ScanInfoViewModel(@NonNull Application application) {
        super(application);
        purchaseService = new RoomPurchaseService(application);
    }

    public Uri getImageUri() {
        return imageUri;
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public void insertPurchase(Purchase purchase) {
        purchaseService.create(purchase);
    }

    public int getCountPurchaseByLastMonth(ClothingType clothingType) {
        LocalDateTime monthAgo = LocalDateTime.now().minusMonths(1);
        return purchaseService.countPurchaseBy(monthAgo, clothingType);
    }
}
