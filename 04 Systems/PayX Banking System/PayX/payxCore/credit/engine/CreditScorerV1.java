package payxCore.credit.engine;

import payxCore.credit.factor.FactorRegistry;
import payxCore.credit.factor.ScoreFactor;
import payxCore.credit.model.CreditScoreRequest;
import payxCore.credit.model.CreditScoreResult;
import payxCore.credit.model.ScoreBand;

import java.util.LinkedHashMap;
import java.util.Map;

public class CreditScorerV1 {
    private final FactorRegistry registry;
    private final WeightedAggregator aggregator;

    public CreditScorerV1(FactorRegistry registry, WeightedAggregator aggregator) {
        this.registry = registry;
        this.aggregator = aggregator;
    }

    public CreditScoreResult score(CreditScoreRequest request)
    {
        Map<String, Integer> breakdown = new LinkedHashMap<>();
        for(ScoreFactor factor: registry.getAll()){
            breakdown.put(factor.getName(), factor.compute(request));
        }
        int finalScore = aggregator.aggregate(breakdown, registry.getAll());
        ScoreBand band = ScoreBand.from(finalScore);
        return new CreditScoreResult(
                finalScore, band, finalScore >= 650, breakdown, request.getModelVersion()
        );
    }
}
