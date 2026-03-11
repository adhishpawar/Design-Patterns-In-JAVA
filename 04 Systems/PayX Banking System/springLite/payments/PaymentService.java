package payments;

import template.PaymentTemplate;
import template.PaymentTemplateFactory;
import validation.ValidationHandler;

//Respo --> process payment and publish event.
public class PaymentService implements IPaymentService {

    private ValidationHandler validator;

    public void setValidator(ValidationHandler validator) {
        this.validator = validator;
    }

    @Override
    public void pay(String accId, double amount, String method) {

        if (!validator.handle(amount, accId, method)) {
            System.out.println("Payment failed in validation chain");
            return;
        }

        PaymentTemplate flow = PaymentTemplateFactory.getFlow(method);
        flow.processPayment(amount, accId, method);
    }
}