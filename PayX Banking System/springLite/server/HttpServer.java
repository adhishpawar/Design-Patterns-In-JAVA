package server;
import core.BeanFactory;
import db.DBConnection;
import java.net.*;
import java.io.*;

import core.SingletonRegistry;
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

        /// Spring-style bean creation
        DBConnection db = BeanFactory.getBean(DBConnection.class);

        String responseBody = "PayX Factory test:\n"
                + "DB instance: " + db + "\n"
                + db.getAccountBalance("ACC999");

        out.write("HTTP/1.1 200 OK\r\n");
        out.write("Content-Type: text/plain\r\n\r\n");
        out.write(responseBody);
        out.flush();
        client.close();
    }
}

