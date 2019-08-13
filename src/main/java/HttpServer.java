import java.io.IOException;
import java.net.*;

public class HttpServer {
    private ServerSocket serverSocket;

    public HttpServer(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void listen() {
        try {
            Socket clientSocket = serverSocket.accept();
            System.out.println(clientSocket);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}