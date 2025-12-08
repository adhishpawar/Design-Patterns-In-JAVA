package facade.dto;

public class PaymentRequest {
    public String accountId;
    public double amount;
    public String method;

    public PaymentRequest(String accountId, double amount, String method) {
        this.accountId = accountId;
        this.amount = amount;
        this.method = method;
    }
}