package template;

public abstract class PaymentTemplate {

    public final void processPayment(double amount, String accountId, String method){
        System.out.println("PAYX payment: " + method);

        validateCustomer(accountId);
        validateBalance(amount);
        debitAccount(amount,accountId);
        recordTransaction(amount, accountId, method);
        sendNotification(amount, accountId, method);
    }

    protected abstract void validateCustomer(String accountId);
    protected abstract void validateBalance(double amount);
    protected abstract void debitAccount(double amount, String accountId);

    protected void recordTransaction(double amount, String accountId, String method){
        System.out.println("Recording transaction  in Ledger");
        System.out.println("Account: " + accountId);
        System.out.println("Amount: " + amount);
        System.out.println("Method: " + method);
    }

    protected void sendNotification(double amount, String accountId, String method) {
        System.out.println("🔔 Payment notification sent to user");
    }


}
