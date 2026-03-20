package payxCore.facade;

import springLite.ioc.core.BeanFactory;
import payxCore.facade.dto.PaymentRequest;
import payxCore.facade.dto.FundTransferRequest;
import payxCore.payments.IPaymentService;
import payxCore.payments.PaymentService;
import springLite.aop.proxy.ProxyFactory;
import payxCore.validation.ValidationChainFactory;
import payxCore.validation.ValidationHandler;
import payxCore.adapter.PayXBankAPI;
import payxCore.adapter.ExternalBankAdapter;
import payxCore.adapter.PayXFundTransferService;

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
