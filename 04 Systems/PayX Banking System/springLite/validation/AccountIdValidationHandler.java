package validation;

public class AccountIdValidationHandler extends BaseValidationHandler{
    @Override
    public boolean handle(double amount, String accountId, String method) {
        if (accountId == null || accountId.isEmpty()) {
            System.out.println("Invalid account ID");
            return false;
        }
        return nextHandler(amount, accountId, method);
    }
}
