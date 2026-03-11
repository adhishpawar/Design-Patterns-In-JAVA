package validation;

public class FraudValidationHandler extends BaseValidationHandler{
    @Override
    public boolean handle(double amount, String accountId, String method) {
        if (amount > 500000) {
            System.out.println("Payment flagged for fraud check!");
            return false;
        }
        return nextHandler(amount, accountId, method);
    }
}
