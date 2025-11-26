package events;

public class LedgerUpdateListener implements PaymentListener {

    public LedgerUpdateListener() {
        System.out.println("🟢 LedgerUpdateListener created");
    }

    @Override
    public void onPaymentCompleted(PaymentEvent event) {
        System.out.println("📒 Ledger -> Updated for account "
                + event.getAccountId()
                + ", amount: " + event.getAmount()
                + ", method: " + event.getMethod());
    }
}
