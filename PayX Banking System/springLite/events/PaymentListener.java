package events;

public interface PaymentListener {
    void onPaymentCompleted(PaymentEvent event);
}
