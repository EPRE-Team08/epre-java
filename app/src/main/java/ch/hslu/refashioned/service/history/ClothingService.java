package ch.hslu.refashioned.service.history;

import java.util.List;

import ch.hslu.refashioned.model.history.Clothing;
import ch.hslu.refashioned.service.CreateService;
import ch.hslu.refashioned.service.DeleteService;
import ch.hslu.refashioned.service.ReadService;
import ch.hslu.refashioned.service.UpdateService;

public interface ClothingService extends ReadService<Clothing>, UpdateService<Clothing>, CreateService<Clothing>, DeleteService<Clothing> {
}
