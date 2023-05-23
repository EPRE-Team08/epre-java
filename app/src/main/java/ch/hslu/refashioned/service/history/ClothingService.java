package ch.hslu.refashioned.service.history;

import ch.hslu.refashioned.model.history.Purchase;
import ch.hslu.refashioned.service.CreateService;
import ch.hslu.refashioned.service.DeleteService;
import ch.hslu.refashioned.service.ReadService;
import ch.hslu.refashioned.service.UpdateService;

public interface ClothingService extends ReadService<Purchase>, UpdateService<Purchase>, CreateService<Purchase>, DeleteService<Purchase> {
}
