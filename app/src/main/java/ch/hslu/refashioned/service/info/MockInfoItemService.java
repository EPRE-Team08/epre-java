package ch.hslu.refashioned.service.info;

import java.util.ArrayList;
import java.util.List;

import ch.hslu.refashioned.model.info.*;

public final class MockInfoItemService implements InfoItemService {
    private final List<InfoItem> items = new ArrayList<>();

    public MockInfoItemService() {
        items.add(new LinkedInfoItem(Category.EDUCATION, "Top 5 Sustainable Brands", "Lorem ipsum dolor sit amet.", "ddg.gg"));
        items.add(new LinkedInfoItem(Category.ADVERTISEMENT, "How about?", "Try some of our newest recommendations.", "ddg.gg"));
        items.add(new InfoItem(Category.INFO, "Score System", "How it works..."));
        items.add(new LinkedInfoItem(Category.EDUCATION, "The latest fast fashion misery", "Lorem ipsum dolor sit amet.", "ddg.gg"));
    }

    @Override
    public List<InfoItem> get() {
        return this.items;
    }
}
