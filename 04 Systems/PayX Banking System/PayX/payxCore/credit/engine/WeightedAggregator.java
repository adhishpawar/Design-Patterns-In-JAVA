package payxCore.credit.engine;

import payxCore.credit.factor.ScoreFactor;

import java.util.List;
import java.util.Map;

public class WeightedAggregator {

    //Score rangeL 300 (worst) to 850(best)
    private static final int MIN_SCORE = 300;
    private static final int MAX_SCORE = 850;

    public int aggregate(Map<String, Integer> factorScores, List<ScoreFactor> factors)
    {
        double rawScore = 0.0;
        for(ScoreFactor f : factors){
            int score = factorScores.getOrDefault(f.getName(), 0);
            rawScore += score * f.getWeight(); //0.0 to 100.0
        }

        //map 0->100 --> 300 - 850
        int finalScore = (int)(MIN_SCORE + (rawScore / 100.0) * MAX_SCORE - MIN_SCORE));
        return Math.max(MIN_SCORE, Math.min(MAX_SCORE, finalScore));
    }
}
