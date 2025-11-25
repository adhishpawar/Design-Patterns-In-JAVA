package server;
import db.DBConnection;
import java.net.*;
import java.io.*;

public class HttpServer {

    public static void start(int port) throws Exception {
        ServerSocket server = new ServerSocket(port);
        System.out.println("🚀 Server started at port: " + port);

        while (true) {
            Socket client = server.accept();
            handleClient(client);
        }
    }

    private static void handleClient(Socket client) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

        String requestLine = in.readLine();
        System.out.println("Request: " + requestLine);

        // ❌ BAD: Every request creates new DB connections
        DBConnection db1 = new DBConnection();
        DBConnection db2 = new DBConnection();

        String responseBody = "PayX test: \n"
                + "db1: " + db1 + "\n"
                + "db2: " + db2 + "\n";

        out.write("HTTP/1.1 200 OK\r\n");
        out.write("Content-Type: text/plain\r\n\r\n");
        out.write(responseBody);
        out.flush();
        client.close();
    }
}

