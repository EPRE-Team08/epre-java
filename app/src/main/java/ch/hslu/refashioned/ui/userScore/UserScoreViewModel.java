package ch.hslu.refashioned.ui.userScore;

import android.app.Application;
import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import ch.hslu.refashioned.model.history.Brand;
import ch.hslu.refashioned.model.history.Purchase;
import ch.hslu.refashioned.model.sustainability.Score;
import ch.hslu.refashioned.repository.history.PurchaseRepo;
import ch.hslu.refashioned.service.history.MockPurchaseService;
import ch.hslu.refashioned.service.history.PurchaseService;
import ch.hslu.refashioned.ui.color.GradientProvider;

public final class UserScoreViewModel extends AndroidViewModel {
    private final PurchaseService service;

    private final MutableLiveData<Integer> score;
    private final MutableLiveData<Color> scoreColor;
    private final int maxScore;
    private final int minScore;

    public UserScoreViewModel(@NonNull Application application) {
        super(application);

        this.service = new PurchaseRepo(new MockPurchaseService());

        this.score = new MutableLiveData<>(service.getAll().stream().mapToInt(Purchase::getScore).sum());
        this.maxScore = service.getAll()
                .stream()
                .map(Purchase::getBrand)
                .map(Brand::getScore)
                .mapToInt(Score::getOverall)
                .max()
                .orElse(0) * service.getAll().size();
        this.minScore = service.getAll()
                .stream()
                .map(Purchase::getBrand)
                .map(Brand::getScore)
                .mapToInt(Score::getOverall)
                .min()
                .orElse(0) * service.getAll().size();

        this.scoreColor = new MutableLiveData<>(getColor(minScore, maxScore, score.getValue()));
    }

    public LiveData<Integer> getScore() {
        return score;
    }

    public LiveData<Color> getScoreColor() {
        return scoreColor;
    }

    private void updateColor(final int min, final int max) {
        this.scoreColor.postValue(getColor(min, max, score.getValue()));
    }

    private Color getColor(final int min, final int max, final int score) {
        var gradient = GradientProvider.getScoreGradient(min, max);
        return gradient.getColor(score);

    }
}