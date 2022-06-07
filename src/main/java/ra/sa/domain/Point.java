package ra.sa.domain;

import java.util.Arrays;

public enum Point {
    ZERO(0, "0"),
    ONE(1, "15"),
    TWO(2, "30"),
    THREE(3, "40"),
    ADVANTAGE(4, "40A");

    private int score;
    private String label;


    Point(int score, String label) {
        this.label = label;
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public String getLabel() {
        return label;
    }

    public static Point of(int score) {
        return Arrays.stream(values()).filter(point -> score == point.getScore()).findAny().orElseThrow(() -> new IllegalArgumentException("no matching point "));
    }
}
