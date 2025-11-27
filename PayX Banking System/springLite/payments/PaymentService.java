package payments;

import core.BeanFactory;
import events.PaymentEvent;
import events.PaymentEventPublisher;
import validation.ValidationHandler;
import validation.ValidationChainFactory;

//Respo --> process payment and publish event.
public class PaymentService {

    private final ValidationHandler validator = ValidationChainFactory.createValidationChain();

    public PaymentService() {
    }

    public void pay(String accountId,double amount, String method) {

        if(!validator.handle(amount, accountId, method))
        {
            System.out.println("Payment Rejected");
            return;
        }

        System.out.println("Payment is valid, proceeding.....");
    }

}