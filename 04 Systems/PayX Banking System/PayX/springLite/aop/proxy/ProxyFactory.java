package springLite.aop.proxy;

import payxCore.payments.IPaymentService;
import payxCore.payments.PaymentService;

public class ProxyFactory {

    public static IPaymentService createPaymentService(PaymentService realService) {
        return new PaymentServiceProxy(realService);
    }
}
