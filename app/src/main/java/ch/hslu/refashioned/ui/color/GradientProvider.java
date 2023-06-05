package ch.hslu.refashioned.ui.color;

import android.graphics.Color;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import ch.hslu.refashioned.model.history.Brand;
import ch.hslu.refashioned.model.history.PurchaseType;
import ch.hslu.refashioned.model.sustainability.Score;

public final class GradientProvider {
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public static ColorFactory getScoreGradient() {
        var max = Stream.of(Brand.values()).mapToInt(b -> b.getScore().getOverall()).max().getAsInt() * Stream.of(PurchaseType.values()).mapToDouble(PurchaseType::getScoreFactor).max().getAsDouble();
        return getScoreGradient(Score.MIN_SCORE, Math.round((float) max));
    }

    public static ColorFactory getScoreGradient(int min, int max) {
        if (min == max)
            min = 0;

        if (min == 0 && max == 0)
            return new ConstantFactory(Color.valueOf(Color.BLACK));

        return new MultiGradientFactory(List.of(Color.valueOf(Color.RED), Color.valueOf(Color.YELLOW), Color.valueOf(Color.GREEN)), Set.of(min, (int) (min + (max - min) * 0.4), max));
    }
}
