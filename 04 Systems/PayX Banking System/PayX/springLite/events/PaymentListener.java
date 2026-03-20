package springLite.events;

public interface PaymentListener {
    void onPaymentCompleted(PaymentEvent event);
}
