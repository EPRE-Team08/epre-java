package ch.hslu.refashioned.model.history;

public enum ClothingType {
    SHIRT(0, "Shirt"),
    SWEATER(1, "Sweater"),
    JACKET(2, "Jacket"),
    PANTS(3, "Pants"),
    SHORTS(4, "Shorts");

    private final String label;
    private final int value;

    ClothingType(final int value, final String label) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return getLabel();
    }
}
