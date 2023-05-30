package ch.hslu.refashioned.ui.color;

import android.graphics.Color;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import ch.hslu.refashioned.model.history.Brand;

public final class GradientProvider {
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public static GradientColor getScoreGradient() {
        var min = Stream.of(Brand.values()).mapToInt(b -> b.getScore().getOverall()).min().getAsInt();
        var max = Stream.of(Brand.values()).mapToInt(b -> b.getScore().getOverall()).max().getAsInt();

        return getScoreGradient(min, max);
    }

    public static GradientColor getScoreGradient(int min, int max) {
        if (min == max) min = 0;

        return new MultiGradient(List.of(Color.valueOf(Color.RED), Color.valueOf(Color.YELLOW), Color.valueOf(Color.GREEN)), Set.of(min, (int) (min + (max - min) * 0.4), max));
    }
}
