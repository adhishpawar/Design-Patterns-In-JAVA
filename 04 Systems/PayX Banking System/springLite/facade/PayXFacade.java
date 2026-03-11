package facade;

import core.BeanFactory;
import facade.dto.PaymentRequest;
import facade.dto.FundTransferRequest;
import payments.IPaymentService;
import payments.PaymentService;
import proxy.ProxyFactory;
import validation.ValidationChainFactory;
import validation.ValidationHandler;
import adapter.PayXBankAPI;
import adapter.ExternalBankAdapter;
import adapter.PayXFundTransferService;

public class PayXFacade {

    private final IPaymentService paymentService;
    private final PayXFundTransferService transferService;

    public PayXFacade() {

        // Validation chain
        ValidationHandler chain = ValidationChainFactory.createValidationChain();

        // Real payment service
        PaymentService real = BeanFactory.getBean(PaymentService.class);
        real.setValidator(chain);

        // Proxy wraps real service
        this.paymentService = ProxyFactory.createPaymentService(real);

        // Transfer service uses Adapter
        PayXBankAPI bankAPI = new ExternalBankAdapter();
        this.transferService = new PayXFundTransferService(bankAPI);
    }

    // ----- PAYMENT API -----
    public void processPayment(PaymentRequest req) {
        paymentService.pay(req.accountId, req.amount, req.method);
    }

    // ----- FUND TRANSFER API -----
    public void transfer(FundTransferRequest req) {
        transferService.transfer(req.amount, req.fromAccount, req.toAccount);
    }
}
