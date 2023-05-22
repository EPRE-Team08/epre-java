package ch.hslu.refashioned.model.history;

import ch.hslu.refashioned.model.sustainability.Score;

public enum Brand {
    PUMA("Puma", new Score(49, 64, 74, 56, 30, 47, 22));
    private final String name;
    private final Score score;

    Brand(String name, Score score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public Score getScore() {
        return score;
    }
}
