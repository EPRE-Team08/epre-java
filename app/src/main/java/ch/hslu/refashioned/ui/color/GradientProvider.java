package ch.hslu.refashioned.ui.color;

import android.graphics.Color;

import java.util.List;
import java.util.Set;

public final class GradientProvider {
    public static GradientColor getScoreGradient(int min, int max) {
        if (min == max)
            min = 0;

        return new MultiGradient(
                List.of(Color.valueOf(Color.RED), Color.valueOf(Color.YELLOW), Color.valueOf(Color.GREEN)),
                Set.of(min, (int) (min + (max - min) * 0.4), max)
        );
    }
}
