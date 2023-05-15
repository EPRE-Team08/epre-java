package ch.hslu.refashioned.model.info;

public class InfoItem {
    private final Category category;
    private final String title;
    private final String brief;

    public InfoItem(final Category category, final String title, final String brief) {
        this.category = category;
        this.title = title;
        this.brief = brief;
    }

    public final Category getCategory() {
        return category;
    }

    public final String getTitle() {
        return title;
    }

    public final String getBrief() {
        return brief;
    }
}
