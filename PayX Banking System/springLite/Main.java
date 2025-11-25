
import core.SingletonRegistry;
import db.DBConnection;
import server.HttpServer;

public class Main {
    public static void main(String[] args) throws Exception {
        // 🔧 Bootstrapping: register singletons
        SingletonRegistry.register(DBConnection.class, DBConnection.getInstance());

        // start HTTP server
        HttpServer.start(8080);
    }
}
