package server;
import core.BeanFactory;
import db.DBConnection;
import java.net.*;
import java.io.*;

import core.SingletonRegistry;
import db.DBConnection;
import payments.PaymentService;

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

        if (requestLine.startsWith("GET /pay")) {

            String method = extractMethod(requestLine);

            if (method == null) {
                out.write("HTTP/1.1 400 Bad Request\r\n");
                out.write("Content-Type: text/plain\r\n\r\n");
                out.write("Missing ?method=UPI/CARD/WALLET\n");
                out.flush();
                client.close();
                return;
            }

            PaymentService service = BeanFactory.getBean(PaymentService.class);
            service.pay("A500", 50000,method);

            out.write("HTTP/1.1 200 OK\r\n");
            out.write("Content-Type: text/plain\r\n\r\n");
            out.write("Payment of 500 INR using " + method + " executed successfully");
            out.flush();
            client.close();
            return;
        }
    }

    private static String extractMethod(String requestLine) {
        int start = requestLine.indexOf("method=");
        if (start == -1) return null;

        String method = requestLine.substring(start + 7);
        int end = method.indexOf(' ');
        if (end != -1) method = method.substring(0, end);

        return method.toUpperCase();
    }

}

