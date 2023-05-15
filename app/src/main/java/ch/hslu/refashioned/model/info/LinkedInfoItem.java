package ch.hslu.refashioned.model.info;

public final class LinkedInfoItem extends InfoItem implements Linked {
    private final String link;

    public LinkedInfoItem(final Category category, final String label, final String brief, final String link) {
        super(category, label, brief);
        this.link = link;
    }

    @Override
    public String getLink() {
        return this.link;
    }
}
