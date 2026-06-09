package springLite.events;

public class FraudMonitoringListener implements PaymentListener {

    public FraudMonitoringListener() {
        System.out.println("🟢 FraudMonitoringListener created");
    }

    @Override
    public void onPaymentCompleted(PaymentEvent event) {
        System.out.println("🕵️ Fraud -> Analyzing payment of "
                + event.getAmount()
                + " via " + event.getMethod()
                + " for account " + event.getAccountId());
    }
}
