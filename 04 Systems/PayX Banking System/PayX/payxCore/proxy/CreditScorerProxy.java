package payxCore.proxy;

import payxCore.credit.engine.ICreditScorer;
import payxCore.credit.model.CreditScoreRequest;
import payxCore.credit.model.CreditScoreResult;
import springLite.aop.proxy.Interceptor;

public class CreditScorerProxy implements ICreditScorer {

    private final ICreditScorer delegate;
    private final Interceptor interceptor;

    public CreditScorerProxy(ICreditScorer delegate, Interceptor interceptor) {
        this.delegate = delegate;
        this.interceptor = interceptor;
    }

    @Override
    public CreditScoreResult score(CreditScoreRequest request) {
        long start = System.currentTimeMillis();
        interceptor.before("CreditScorerV1", "score", new Object[]{request.getUserId(), request.getModelVersion()});

        try {
            CreditScoreResult result = delegate.score(request);
            long duration = System.currentTimeMillis() - start;
            interceptor.after("CreditScorerV1", "score", result, duration);
            return result;
        } catch (Exception e) {
            // log failure, rethrow
            throw e;
        }

    }
}
