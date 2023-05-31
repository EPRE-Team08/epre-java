package ch.hslu.refashioned.repository.history;

import java.time.LocalDateTime;
import java.util.List;

import ch.hslu.refashioned.service.history.ScoreService;

public final class ScoreRepo implements ScoreService {
    private final ScoreService service;

    public ScoreRepo(ScoreService scoreService) {
        this.service = scoreService;
    }

    @Override
    public List<Integer> getAll() {
        return service.getAll();
    }

    @Override
    public Integer getById(LocalDateTime dateTime) {
        return service.getById(dateTime);
    }

    @Override
    public int getTotal() {
        return service.getTotal();
    }

    @Override
    public int getCount() {
        return service.getCount();
    }
}
