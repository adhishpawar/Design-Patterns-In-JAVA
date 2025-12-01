package payments;

import template.PaymentTemplate;
import template.PaymentTemplateFactory;
import validation.ValidationHandler;

//Respo --> process payment and publish event.
public class PaymentService {

    private final ValidationHandler validator;

    public PaymentService(ValidationHandler validator) {
        this.validator = validator;
    }

    public void pay(String accountId,double amount, String method) {

        if(!validator.handle(amount, accountId, method))
        {
            System.out.println("Payment Rejected");
            return;
        }

        PaymentTemplate flow = PaymentTemplateFactory.getFlow(method);
        flow.processPayment(amount, accountId, method);
    }

}