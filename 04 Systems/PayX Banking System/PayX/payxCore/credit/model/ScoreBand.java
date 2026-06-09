package payxCore.credit.model;

public enum ScoreBand {

    POOR (300, 499),
    FAIR (500, 649),
    GOOD (650, 749),
    EXCELLENT (750, 850);

    private final int min;
    private final int max;

    ScoreBand(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public static ScoreBand from(int score){
        for (ScoreBand b : values()) {
            if (score >= b.min && score <= b.max) {
                return b;
            }
        }
        throw new IllegalArgumentException("Score out of range: " + score);
    }
}