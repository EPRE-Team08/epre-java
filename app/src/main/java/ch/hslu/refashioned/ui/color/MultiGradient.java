package ch.hslu.refashioned.ui.color;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public final class MultiGradient implements GradientColor {
    private final List<Gradient> gradients = new ArrayList<>();

    public MultiGradient(final List<Color> colors, final Set<Integer> boundaries) {
        if (colors.size() != boundaries.size())
            throw new IllegalArgumentException("The size of colors and boundaries has to be equal");

        final var borders = boundaries.stream().mapToInt(value -> value).sorted().toArray();

        for (int i = 0; i < borders.length - 1; i++) {
            var lower = borders[i];
            var upper = borders[i + 1];
            var lowerColor = colors.get(i);
            var upperColor = colors.get(i + 1);

            this.gradients.add(new Gradient(upper, lower, upperColor, lowerColor));
        }
    }

    @Override
    public Color getColor(final int value) {
        return this.gradients.stream()
                .filter(g -> g.getMin() <= value && g.getMax() >= value)
                .findAny()
                .get()
                .getColor(value);
    }
}
