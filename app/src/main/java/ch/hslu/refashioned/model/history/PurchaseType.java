package ch.hslu.refashioned.model.history;

public enum PurchaseType {
    FirstHand("First Hand"), SecondHand("Second Hand");

    private final String label;

    PurchaseType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
