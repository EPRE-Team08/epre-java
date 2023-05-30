package ch.hslu.refashioned.ui.userScore;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import ch.hslu.refashioned.model.history.Purchase;
import ch.hslu.refashioned.repository.history.PurchaseRepo;
import ch.hslu.refashioned.service.history.MockPurchaseService;
import ch.hslu.refashioned.service.history.PurchaseService;

public final class UserScoreViewModel extends AndroidViewModel {
    private final PurchaseService service;

    private final LiveData<Integer> score;

    public UserScoreViewModel(@NonNull Application application) {
        super(application);

        this.service = new PurchaseRepo(new MockPurchaseService());
        this.score = new MutableLiveData<>(service.getAll().stream().mapToInt(Purchase::getScore).sum());
    }

    public LiveData<Integer> getScore() {
        return score;
    }
}
