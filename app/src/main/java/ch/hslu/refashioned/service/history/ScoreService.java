package ch.hslu.refashioned.service.history;

import java.time.LocalDateTime;

import ch.hslu.refashioned.service.ReadService;

public interface ScoreService extends ReadService<Integer, LocalDateTime> {
    int getTotal();
    int getCount();
}
