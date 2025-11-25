
import core.SingletonRegistry;
import db.DBConnection;
import server.HttpServer;

public class Main {
    public static void main(String[] args) throws Exception {
        // start HTTP server
        HttpServer.start(8080);
    }
}
