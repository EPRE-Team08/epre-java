package ch.hslu.refashioned.ui.color;

import android.graphics.Color;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import ch.hslu.refashioned.model.history.Brand;
import ch.hslu.refashioned.model.sustainability.Score;

public final class GradientProvider {
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public static ColorFactory getScoreGradient() {
        return getScoreGradient(Score.MIN_SCORE, Score.MAX_SCORE);
    }

    public static ColorFactory getScoreGradient(int min, int max) {
        if (min == max)
            min = 0;

        if (min == 0 && max == 0)
            return new ConstantFactory(Color.valueOf(Color.BLACK));

        return new MultiGradientFactory(List.of(Color.valueOf(Color.RED), Color.valueOf(Color.YELLOW), Color.valueOf(Color.GREEN)), Set.of(min, (int) (min + (max - min) * 0.4), max));
    }
}
