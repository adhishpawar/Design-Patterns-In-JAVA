package facade.dto;

public class FundTransferRequest {
    public String fromAccount;
    public String toAccount;
    public double amount;

    public FundTransferRequest(String fromAccount, String toAccount, double amount) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
    }
}
