package ch.hslu.refashioned.ui.info;

import androidx.lifecycle.ViewModel;

import java.util.List;

import ch.hslu.refashioned.model.info.InfoItem;
import ch.hslu.refashioned.repository.info.InfoItemRepo;
import ch.hslu.refashioned.service.info.InfoItemService;
import ch.hslu.refashioned.service.info.MockInfoItemService;

public final class InfoViewModel extends ViewModel {
    private final InfoItemService infoItemService = new InfoItemRepo(new MockInfoItemService());
    private final List<InfoItem> infoItems;

    public InfoViewModel() {
        this.infoItems = infoItemService.get();
    }

    public List<InfoItem> get() {
        return this.infoItems;
    }
}