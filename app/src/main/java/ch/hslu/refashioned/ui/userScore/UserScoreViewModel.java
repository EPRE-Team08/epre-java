package ch.hslu.refashioned.ui.userScore;

import android.app.Application;
import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.util.stream.Stream;

import ch.hslu.refashioned.model.history.Brand;
import ch.hslu.refashioned.model.history.Purchase;
import ch.hslu.refashioned.repository.history.PurchaseRepo;
import ch.hslu.refashioned.service.history.PurchaseService;
import ch.hslu.refashioned.service.history.RoomPurchaseService;
import ch.hslu.refashioned.ui.color.GradientProvider;

public final class UserScoreViewModel extends AndroidViewModel {
    private final PurchaseService service;

    private final Integer score;
    private final int maxScore;
    private final int minScore;

    public UserScoreViewModel(@NonNull Application application) {
        super(application);

        this.service = new PurchaseRepo(new RoomPurchaseService(application));

        this.score = service.getAll().stream().mapToInt(Purchase::getScore).sum();
        this.maxScore = Stream.of(Brand.values()).mapToInt(b -> b.getScore().getOverall()).max().orElse(0) * service.getAll().size();
        this.minScore = Stream.of(Brand.values()).mapToInt(b -> b.getScore().getOverall()).min().orElse(0) * service.getAll().size();
    }

    public int getScore() {
        return this.score;
    }

    public Color getScoreColor() {
        return getColor(this.minScore, this.maxScore, this.score);
    }

    private Color getColor(final int min, final int max, final int score) {
        var gradient = GradientProvider.getScoreGradient(min, max);
        return gradient.getColor(score);

    }
}