package ch.hslu.refashioned.ui.color;

import android.graphics.Color;

public final class ConstantFactory implements ColorFactory {
    private final Color color;

    public ConstantFactory(Color color) {
        this.color = color;
    }

    @Override
    public Color getColor(int value) {
        return color;
    }
}
