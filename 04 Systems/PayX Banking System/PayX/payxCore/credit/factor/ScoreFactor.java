package payxCore.credit.factor;

import payxCore.credit.model.CreditScoreRequest;

public interface ScoreFactor {
    String getName();
    double getWeight();
    int compute(CreditScoreRequest request);
}
