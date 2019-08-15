import java.io.*;
import java.net.ServerSocket;

public class Main {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5000);
        HttpServer server = new HttpServer(serverSocket, new PrintWriter(System.out, true));

        server.start();
    }
}