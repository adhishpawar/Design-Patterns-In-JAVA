package payxCore.credit.factor;

import payxCore.credit.model.CreditScoreRequest;

public class IncomeExpenseFactor implements ScoreFactor{

    @Override
    public String getName() {
        return "IncomeExpense";
    }

    @Override
    public double getWeight() {
        return 0.55;
    }

    @Override
    public int compute(CreditScoreRequest request) {
        if(request.getMonthlyExpense() == 0) {
            return 0;
        }
        double ratio = request.getMonthlyIncome() / request.getMonthlyExpense();
        if(ratio >= 2.0) return 100;
        if(ratio < 1.0) return 0;

        //Linear interpolation between 1 and 2
        return (int) ((ratio - 1.0) * 100);

    }
}
