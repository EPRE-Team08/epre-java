package ch.hslu.refashioned.repository.history;

import java.time.LocalDateTime;
import java.util.List;

import ch.hslu.refashioned.service.history.ScoreService;

public final class ScoreRepo implements ScoreService {
    private final ScoreService scoreService;

    public ScoreRepo(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @Override
    public List<Integer> getAll() {
        return scoreService.getAll();
    }

    @Override
    public Integer getById(LocalDateTime dateTime) {
        return scoreService.getById(dateTime);
    }

    @Override
    public int getTotal() {
        return scoreService.getTotal();
    }
}
