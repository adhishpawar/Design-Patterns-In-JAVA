package payxCore.credit.model;

import java.util.Map;

public final class CreditScoreResult {

    private final int score;
    private final ScoreBand band;
    private final boolean loanEligible;
    private final Map<String, Integer> factorBreakDown;
    private final String modelVersion;

    public CreditScoreResult(int score, ScoreBand band, boolean loanEligible, Map<String, Integer> factorBreakDown, String modelVersion) {
        this.score = score;
        this.band = band;
        this.loanEligible = loanEligible;
        this.factorBreakDown = factorBreakDown;
        this.modelVersion = modelVersion;
    }


}
