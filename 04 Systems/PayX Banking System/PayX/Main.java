import payxCore.credit.engine.CreditScorerV1;
import payxCore.credit.engine.ICreditScorer;
import payxCore.credit.engine.WeightedAggregator;
import payxCore.credit.factor.FactorRegistry;
import payxCore.credit.factor.IncomeExpenseFactor;
import payxCore.credit.factor.TxnConsistencyFactor;
import payxCore.facade.PayXFacade;
import payxCore.facade.dto.PaymentRequest;
import payxCore.facade.dto.FundTransferRequest;
import payxCore.proxy.CreditScorerProxy;
import springLite.aop.proxy.Interceptor;

public class Main {
    public static void main(String[] args) {
        buildScoringEngine();
    }

    public static ICreditScorer buildScoringEngine() {
        return  null;
    }
}
