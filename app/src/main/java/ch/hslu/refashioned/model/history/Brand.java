package ch.hslu.refashioned.model.history;

import ch.hslu.refashioned.model.sustainability.Score;

public enum Brand {
    FILA(0, "Fila", new Score(7, 7, 7, 0, 9, 12, 5)),
    ASICS(1, "ASICS", new Score(29, 50, 26, 19, 35, 19, 24)),
    PUMA(2, "Puma", new Score(49, 64, 74, 56, 30, 47, 22));

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
