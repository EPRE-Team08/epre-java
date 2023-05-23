package ch.hslu.refashioned.model.history;

public enum PurchaseType {
    FIRST_HAND(0, "First Hand"), SECOND_HAND(1, "Second Hand");

    private final String label;
    private final int value;

    PurchaseType(final int value, final String label) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public int getValue() {
        return value;
    }
}
