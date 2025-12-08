package proxy;

import payments.IPaymentService;
import payments.PaymentService;

public class ProxyFactory {

    public static IPaymentService createPaymentService(PaymentService realService) {
        return new PaymentServiceProxy(realService);
    }
}
