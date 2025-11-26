
import core.BeanFactory;
import core.SingletonRegistry;
import db.DBConnection;
import events.*;
import payments.PaymentService;
import server.HttpServer;

public class Main {
    public static void main(String[] args) throws Exception {

        // 1. Create publisher
        PaymentEventPublisher publisher = new PaymentEventPublisher();

        // 2. Register listeners
        publisher.register(BeanFactory.getBean(EmailNotificationListener.class));
        publisher.register(BeanFactory.getBean(SmsNotificationListener.class));
        publisher.register(BeanFactory.getBean(LedgerUpdateListener.class));
        publisher.register(BeanFactory.getBean(FraudMonitoringListener.class));

        // 3. Create PaymentService with publisher
        PaymentService paymentService = new PaymentService(publisher);

        paymentService.pay("ACC123", 500.0, "UPI");

        // start HTTP server
        // HttpServer.start(8080);
    }
}
