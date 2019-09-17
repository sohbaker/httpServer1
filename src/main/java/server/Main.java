package server;

import java.io.*;
import java.net.ServerSocket;

public class Main {
    private static int port;

    public static void main(String[] args) throws IOException {
        getPort(args);
        ServerSocket serverSocket = new ServerSocket(port);
        HttpServer server = new HttpServer(serverSocket, new PrintWriter(System.out, true));

        server.start();
    }

    private static void getPort(String[] args) {
        if (args.length == 1) {
            port = Integer.parseInt(args[0]);
        } else {
            port = 5000;
        }
    }
}