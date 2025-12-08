
import adapter.ExternalBankAdapter;
import adapter.PayXBankAPI;
import adapter.PayXFundTransferService;
import core.BeanFactory;
import core.SingletonRegistry;
import db.DBConnection;
import events.*;
import facade.PayXFacade;
import facade.dto.FundTransferRequest;
import facade.dto.PaymentRequest;
import payments.PaymentService;
import server.HttpServer;

public class Main {
    public static void main(String[] args) throws Exception {
        PayXFacade facade = new PayXFacade();

        // PAYMENT
        PaymentRequest payment = new PaymentRequest("ACC123", 750.0, "UPI");
        facade.processPayment(payment);

        // FUND TRANSFER
        FundTransferRequest transfer = new FundTransferRequest("ACC111", "ACC999", 1200.0);
        facade.transfer(transfer);
    }
}
