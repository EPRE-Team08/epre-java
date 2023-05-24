package ch.hslu.refashioned.model.info;

public enum Category {
    ADVERTISEMENT("Advertisement"),
    INFO("Info"),
    EDUCATION("Education");

    private final String label;

    Category(String label) {
        this.label = label;
    }

    public final String getLabel() {
        return label;
    }
}
