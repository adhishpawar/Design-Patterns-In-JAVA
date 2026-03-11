package adapter;

public class PayXFundTransferService {

    private final PayXBankAPI api;

    public PayXFundTransferService(PayXBankAPI api) {
        this.api = api;
    }

    public void transfer(double amount, String fromAcc, String toAcc) {
        boolean ok = api.transfer(amount, fromAcc, toAcc);

        if (ok)
            System.out.println(" PayX Transfer Completed Successfully.");
        else
            System.out.println(" PayX Transfer Failed.");
    }
}
