
import adapter.ExternalBankAdapter;
import adapter.PayXBankAPI;
import adapter.PayXFundTransferService;
import core.BeanFactory;
import core.SingletonRegistry;
import db.DBConnection;
import events.*;
import payments.PaymentService;
import server.HttpServer;

public class Main {
    public static void main(String[] args) throws Exception {

        // start HTTP server
//        HttpServer.start(8080);

        PayXBankAPI bankAPI = new ExternalBankAdapter();
        PayXFundTransferService transferService = new PayXFundTransferService(bankAPI);

        transferService.transfer(500.0, "ACC123", "ACC999");
    }
}
