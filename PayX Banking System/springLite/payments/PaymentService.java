package payments;

import core.BeanFactory;

public class PaymentService {

    public void pay(double amount, String method) {

        PaymentStrategy strategy = switch (method) {
            case "UPI" -> BeanFactory.getBean(UpiPayment.class);
            case "CARD" -> BeanFactory.getBean(CardPayment.class);
            case "WALLET" -> BeanFactory.getBean(WalletPayment.class);
            default -> throw new RuntimeException("Unknown payment method: " + method);
        };

        strategy.pay(amount);
    }
}
