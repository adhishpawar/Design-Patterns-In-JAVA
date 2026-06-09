package payxCore.credit.engine;

import payxCore.credit.model.CreditScoreRequest;
import payxCore.credit.model.CreditScoreResult;

public interface ICreditScorer {
    CreditScoreResult score(CreditScoreRequest request);
}
