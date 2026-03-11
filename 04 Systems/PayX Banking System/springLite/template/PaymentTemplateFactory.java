package template;

import core.BeanFactory;

public class PaymentTemplateFactory {

    public static PaymentTemplate getFlow(String method) {
        return switch(method) {
            case "UPI" -> BeanFactory.getBean(UpiPaymentFlow.class);
            case "CARD" -> BeanFactory.getBean(CardPaymentFlow.class);
            default -> throw new RuntimeException("Unknown payment method: " + method);
        };
    }
}
