package ch.hslu.refashioned.repository.info;

import java.util.List;

import ch.hslu.refashioned.model.info.InfoItem;
import ch.hslu.refashioned.service.info.InfoItemService;

public final class InfoItemRepo implements InfoItemService {
    private final InfoItemService infoItemService;

    public InfoItemRepo(InfoItemService infoItemService) {
        this.infoItemService = infoItemService;
    }

    @Override
    public List<InfoItem> getAll() {
        return infoItemService.getAll();
    }

    @Override
    public InfoItem getById(String id) {
        return infoItemService.getById(id);
    }
}
