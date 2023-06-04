package ch.hslu.refashioned.ui.home;

import android.net.Uri;

import androidx.lifecycle.ViewModel;

import ch.hslu.refashioned.model.history.Brand;

public class ScanViewModel extends ViewModel {
    private Uri imageUri;
    private Brand brand;

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
}