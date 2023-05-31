package ch.hslu.refashioned.ui.color;

import android.graphics.Color;

public final class GradientFactory implements ColorFactory {
    private final int max;
    private final int min;

    private final Color maxColor;
    private final Color minColor;

    public GradientFactory(final int max, final int min, final Color maxColor, final Color minColor) {
        if (!minColor.getColorSpace().equals(maxColor.getColorSpace()))
            throw new IllegalArgumentException("maxColor and minColor must have the same ColorSpace");

        this.max = max;
        this.min = min;
        this.maxColor = maxColor;
        this.minColor = minColor;
    }

    public int getMax() {
        return max;
    }

    public int getMin() {
        return min;
    }

    @Override
    public Color getColor(final int value) {
        final var percentile = (float) (value - min) / (max - min);

        float[] components = minColor.getComponents();
        for (int i = 0; i < minColor.getComponents().length; i++) {
            var lowerBound = minColor.getComponents()[i];
            var upperBound = maxColor.getComponents()[i];
            var newOffset = (upperBound - lowerBound) * percentile;

            components[i] = newOffset + lowerBound;
        }

        return Color.valueOf(components, minColor.getColorSpace());
    }
}
