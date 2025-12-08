package proxy;

import payments.IPaymentService;

public class PaymentServiceProxy implements IPaymentService {

    private final IPaymentService realService;

    public PaymentServiceProxy(IPaymentService realService) {
        this.realService = realService;
    }

    @Override
    public void pay(String accId, double amount, String method) {

        System.out.println("\n [Proxy] Security Validation...");
        System.out.println(" [Proxy] Logging Payment Request...");
        long start = System.currentTimeMillis();

        realService.pay(accId, amount, method);

        long end = System.currentTimeMillis();
        System.out.println("[Proxy] Execution Time: " + (end - start) + " ms");
        System.out.println("[Proxy] Audit Recorded for " + accId);
    }
}
