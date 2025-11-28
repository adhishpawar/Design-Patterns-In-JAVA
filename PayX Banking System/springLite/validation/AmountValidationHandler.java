package validation;

public class AmountValidationHandler extends BaseValidationHandler{

    @Override
    public boolean handle(double amount, String accountId, String method){
        if(amount <= 0)
        {
            System.out.println("Invalid Amount");
            return false;
        }
        return nextHandler(amount,accountId,method);
    }
}
