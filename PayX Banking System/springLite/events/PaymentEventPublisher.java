package events;

import java.util.ArrayList;
import java.util.List;

public class PaymentEventPublisher {

    private final List<PaymentListener> listeners = new ArrayList<>();

    public void register(PaymentListener listener){
        listeners.add(listener);
    }

    public void unregister(PaymentListener listener) {
        listeners.remove(listener);
    }

    public void publishEvent(PaymentEvent event) {
        for (PaymentListener listener : listeners) {
            listener.onPaymentCompleted(event);
        }
    }
}
