package ch.hslu.refashioned.ui.home;

import android.app.Application;
import android.content.Context;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ch.hslu.refashioned.model.history.Purchase;
import ch.hslu.refashioned.repository.history.PurchaseRepo;
import ch.hslu.refashioned.service.history.PurchaseService;
import ch.hslu.refashioned.service.history.RoomPurchaseService;

public class ScanViewModel extends ViewModel {
    private Uri savedUri;

    public Uri getSavedUri() {
        return savedUri;
    }

    public void setSavedUri(Uri savedUri) {
        this.savedUri = savedUri;
    }
}