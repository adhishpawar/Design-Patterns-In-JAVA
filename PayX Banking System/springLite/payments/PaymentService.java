package payments;

import core.BeanFactory;
import events.PaymentEvent;
import events.PaymentEventPublisher;


//Respo --> process payment and publish event.
public class PaymentService {

    private final PaymentEventPublisher publisher;

    public PaymentService(PaymentEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void pay(String accountId,double amount, String method) {

        System.out.println("processing payment of " + amount + "via" + method + "For Amount " + accountId);

        //assume success for now
        String status = "SUCCESS";

        PaymentEvent event = new PaymentEvent(accountId, amount, method, status);

        publisher.publishEvent(event);

    }

}