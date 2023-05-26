package ch.hslu.refashioned.service.history;

import java.time.LocalDateTime;

import ch.hslu.refashioned.model.history.Purchase;
import ch.hslu.refashioned.service.CreateService;
import ch.hslu.refashioned.service.DeleteService;
import ch.hslu.refashioned.service.ReadService;
import ch.hslu.refashioned.service.UpdateService;

public interface PurchaseService extends CreateService<Purchase>, ReadService<Purchase, LocalDateTime>, UpdateService<Purchase>, DeleteService<Purchase> {
}
