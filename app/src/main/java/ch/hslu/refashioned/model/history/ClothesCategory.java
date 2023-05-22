package ch.hslu.refashioned.model.history;

public enum ClothesCategory {
    TROUSERS("Trousers"),
    SWEATER("Sweater"),
    SHOES("Shoes"),
    SHIRT("Shirt"),
    DRESS("Dress"),
    JACKET("Jacket");

    private final String name;

    ClothesCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
