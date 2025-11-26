package events;

public class EmailNotificationListener implements PaymentListener {

    public EmailNotificationListener() {
        System.out.println("🟢 EmailNotificationListener created");
    }

    @Override
    public void onPaymentCompleted(PaymentEvent event) {
        System.out.println("📧 Email -> Payment of " + event.getAmount()
                + " via " + event.getMethod()
                + " for account " + event.getAccountId()
                + " [status: " + event.getStatus() + "]");
    }
}