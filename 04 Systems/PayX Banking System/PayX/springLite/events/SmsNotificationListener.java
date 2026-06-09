package springLite.events;

public class SmsNotificationListener implements PaymentListener {

    public SmsNotificationListener() {
        System.out.println("🟢 SmsNotificationListener created");
    }

    @Override
    public void onPaymentCompleted(PaymentEvent event) {
        System.out.println("📱 SMS -> Payment of " + event.getAmount()
                + " via " + event.getMethod()
                + " for account " + event.getAccountId());
    }
}
