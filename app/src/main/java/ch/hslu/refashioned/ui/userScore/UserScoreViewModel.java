package ch.hslu.refashioned.ui.userScore;

import android.app.Application;
import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.util.stream.Stream;

import ch.hslu.refashioned.model.history.Brand;
import ch.hslu.refashioned.repository.history.ScoreRepo;
import ch.hslu.refashioned.service.history.RoomScoreService;
import ch.hslu.refashioned.service.history.ScoreService;
import ch.hslu.refashioned.ui.color.GradientProvider;

public final class UserScoreViewModel extends AndroidViewModel {
    private final ScoreService service;

    public UserScoreViewModel(@NonNull Application application) {
        super(application);

        this.service = new ScoreRepo(new RoomScoreService(application));
    }

    public int getScore() {
        return this.service.getTotal();
    }

    public Color getScoreColor() {
        return getColor(getMinScore(), getMaxScore(), getScore());
    }

    private Color getColor(final int min, final int max, final int score) {
        var gradient = GradientProvider.getScoreGradient(min, max);
        return gradient.getColor(score);
    }

    private int getMaxScore() {
        return Stream.of(Brand.values()).mapToInt(b -> b.getScore().getOverall()).max().orElse(0) * service.getCount();
    }

    private int getMinScore() {
        return Stream.of(Brand.values()).mapToInt(b -> b.getScore().getOverall()).min().orElse(0) * service.getCount();
    }
}