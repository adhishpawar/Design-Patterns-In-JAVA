
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

        PaymentService service = new PaymentService(
                validation.ValidationChainFactory.createValidationChain()
        );

        System.out.println("\n--- UPI PAYMENT ---");
        service.pay("ACC123", 500, "UPI");

        System.out.println("\n--- CARD PAYMENT ---");
        service.pay("ACC888", 1200, "CARD");
    }
}
