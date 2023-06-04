package ch.hslu.refashioned.ui.color;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public final class MultiGradientFactory implements ColorFactory {
    private final List<GradientFactory> gradientFactories = new ArrayList<>();
    private final Color fallbackColor;

    public MultiGradientFactory(final List<Color> colors, final Set<Integer> boundaries, Color fallbackColor) {
        this.fallbackColor = fallbackColor;
        if (colors.size() != boundaries.size())
            throw new IllegalArgumentException("The size of colors and boundaries has to be equal");

        final var borders = boundaries.stream().mapToInt(value -> value).sorted().toArray();

        for (int i = 0; i < borders.length - 1; i++) {
            var lower = borders[i];
            var upper = borders[i + 1];
            var lowerColor = colors.get(i);
            var upperColor = colors.get(i + 1);

            this.gradientFactories.add(new GradientFactory(upper, lower, upperColor, lowerColor));
        }
    }

    public MultiGradientFactory(final List<Color> colors, final Set<Integer> boundaries) {
        this(colors, boundaries, Color.valueOf(Color.BLACK));
    }

    @Override
    public Color getColor(final int value) {
        return this.gradientFactories.stream()
                .filter(g -> g.getMin() <= value && g.getMax() >= value)
                .findAny()
                .map(factory -> factory.getColor(value))
                .orElseGet(() -> fallbackColor);
    }
}
