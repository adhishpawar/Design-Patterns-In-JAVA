package events;

public class PaymentEvent {

    private final String accountId;
    private final double amount;
    private final String method;
    private final String status;

    public PaymentEvent(String accountId, double amount, String method, String status)
    {
        this.accountId = accountId;
        this.amount = amount;
        this.method = method;
        this.status = status;
    }

    public String getAccountId() {
        return accountId;
    }

    public double getAmount() {
        return amount;
    }

    public String getMethod() {
        return method;
    }

    public String getStatus() {
        return status;
    }
}
