package facade;

import core.BeanFactory;
import facade.dto.PaymentRequest;
import facade.dto.FundTransferRequest;
import payments.PaymentService;
import validation.ValidationChainFactory;
import validation.ValidationHandler;
import adapter.PayXBankAPI;
import adapter.ExternalBankAdapter;
import adapter.PayXFundTransferService;

public class PayXFacade {

    private final PaymentService paymentService;
    private final PayXFundTransferService fundTransferService;


    public PayXFacade()
    {
        // ---- Setup Validation Chain ----
        ValidationHandler validator = ValidationChainFactory.createValidationChain();

        this.paymentService = BeanFactory.getBean(PaymentService.class);
        this.paymentService.setValidator(validator);

        // ---- Adapter + External Bank Integration ----
        PayXBankAPI bankAPI = BeanFactory.getBean(ExternalBankAdapter.class);
        this.fundTransferService = new PayXFundTransferService(bankAPI);

        System.out.println("PayXFacade Initialized Successfully.");
    }

    public void processPayment(PaymentRequest req)
    {
      System.out.println("Processing the Payment");
      paymentService.pay(req.accountId, req.amount, req.method);
    }

    public void transfer(FundTransferRequest req)
    {
        System.out.println("Transferring the Funds....");
        fundTransferService.transfer(req.amount, req.fromAccount, req.toAccount);
    }
}
