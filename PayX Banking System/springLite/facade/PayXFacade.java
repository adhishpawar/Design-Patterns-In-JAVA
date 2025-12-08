package facade;

import core.BeanFactory;
import facade.dto.PaymentRequest;
import payments.IPaymentService;
import payments.PaymentService;
import proxy.ProxyFactory;
import validation.ValidationChainFactory;
import validation.ValidationHandler;

public class PayXFacade {

    private final IPaymentService paymentService;

    public PayXFacade() {

        // Create validation chain
        ValidationHandler chain = ValidationChainFactory.createValidationChain();

        // Get REAL service from BeanFactory
        PaymentService real = BeanFactory.getBean(PaymentService.class);

        // Inject validator
        real.setValidator(chain);

        // Create proxy around the real service
        this.paymentService = ProxyFactory.createPaymentService(real);
    }

    public void processPayment(PaymentRequest request) {
        paymentService.pay(request.accountId, request.amount, request.method);
    }
}
