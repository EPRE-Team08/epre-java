package ch.hslu.refashioned.ui.scanInfo;

import android.app.Application;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import ch.hslu.refashioned.model.history.Brand;

public class ScanInfoViewModel extends AndroidViewModel {
    private Uri imageUri;
    private Brand brand;

    public ScanInfoViewModel(@NonNull Application application) {
        super(application);
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
}
