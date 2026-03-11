package template;

public class CardPaymentFlow extends PaymentTemplate {

    @Override
    protected void validateCustomer(String accountId) {
        System.out.println(" Card: validating card owner: " + accountId);
    }

    @Override
    protected void validateBalance(double amount) {
        System.out.println(" Card: checking credit limit for: " + amount);
    }

    @Override
    protected void debitAccount(double amount, String accountId) {
        System.out.println(" Card: charging card for: " + amount);
    }
}
