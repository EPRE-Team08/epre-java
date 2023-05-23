package ch.hslu.refashioned.model.map;

public enum Type {
    THRIFT("Thrift Store"),
    SHOP("Shop");

    private final String label;

    Type(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
