package ch.hslu.refashioned.model.history;

import ch.hslu.refashioned.model.sustainability.Score;

public enum Brand {
    PUMA(0, "Puma", new Score(49, 64, 74, 56, 30, 47, 22));
    private final int value;
    private final String name;
    private final Score score;

    Brand(int value, String name, Score score) {
        this.value = value;
        this.name = name;
        this.score = score;
    }

    public String getLabel() {
        return name;
    }

    public Score getScore() {
        return score;
    }

    public int getValue() {
        return value;
    }
}
