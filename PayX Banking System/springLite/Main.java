
import core.BeanFactory;
import core.SingletonRegistry;
import db.DBConnection;
import events.*;
import payments.PaymentService;
import server.HttpServer;

public class Main {
    public static void main(String[] args) throws Exception {

        // start HTTP server
         HttpServer.start(8080);
    }
}
