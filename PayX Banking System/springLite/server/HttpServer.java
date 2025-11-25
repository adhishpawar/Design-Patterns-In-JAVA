package server;

import java.io.*;
import java.net.*;

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

        // Read the request line
        String requestLine = in.readLine(); 
        System.out.println("Request: " + requestLine);

        out.write("HTTP/1.1 200 OK\r\n");
        out.write("Content-Type: text/plain\r\n\r\n");
        out.write("Hello — PayX test server running!\n");
        out.flush();
        client.close();
    }
}
