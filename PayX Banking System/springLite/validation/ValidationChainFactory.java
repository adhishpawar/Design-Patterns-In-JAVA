package validation;


//Like Spring Bean configuration
public class ValidationChainFactory {

    public static ValidationHandler createValidationChain(){
        AmountValidationHandler amount = new AmountValidationHandler();
        AccountIdValidationHandler account = new AccountIdValidationHandler();
        FraudValidationHandler fraud = new FraudValidationHandler();

        amount.setNext(account);
        amount.setNext(fraud);

        return account; //--> root handler
    }
}
