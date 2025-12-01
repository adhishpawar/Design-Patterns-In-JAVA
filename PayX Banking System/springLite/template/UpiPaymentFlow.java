package template;

public class UpiPaymentFlow extends PaymentTemplate{

    @Override
    protected void validateCustomer(String accountId) {
        System.out.println("UPI: validating customer KYC: " + accountId);
    }

    @Override
    protected void validateBalance(double amount) {
        System.out.println("UPI: checking balance for: " + amount);
    }

    @Override
    protected void debitAccount(double amount, String accountId) {
        System.out.println("UPI: debiting account: " + accountId);
    }
}
