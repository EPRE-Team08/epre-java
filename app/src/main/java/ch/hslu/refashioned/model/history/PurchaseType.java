package ch.hslu.refashioned.model.history;

public enum PurchaseType {
    FIRST_HAND(0, "First Hand", 1), SECOND_HAND(1, "Second Hand", 1.5f);

    private final String label;
    private final int value;
    private final float scoreFactor;

    PurchaseType(final int value, final String label, float scoreFactor) {
        this.label = label;
        this.value = value;
        this.scoreFactor = scoreFactor;
    }

    public String getLabel() {
        return label;
    }

    public int getValue() {
        return value;
    }

    public float getScoreFactor() {
        return scoreFactor;
    }
}
