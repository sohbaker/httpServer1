import java.io.*;
import java.net.*;

public class HttpServer {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private BufferedReader clientInput;
    private PrintWriter output;

    public HttpServer(ServerSocket serverSocket, PrintWriter output) {
        this.serverSocket = serverSocket;
        this.output = output;
    }

    public void listen() {
        try {
            clientSocket = serverSocket.accept();
            setUpIOStreams();
        } catch (IOException ex) {
            output.println(ex);
        }
    }

    public String parseInput() {
        String request = "";
        try {
            request = clientInput.readLine();
        } catch (IOException ex) {
            output.println(ex);
        }
        return request;
    }

    private void setUpIOStreams() {
        try {
            clientInput = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter clientOutput = new PrintWriter(clientSocket.getOutputStream(), true);
            output.println("IO streams created");
        } catch (IOException ex) {
            output.println(ex);
        }
    }

    public void stop() {
        try {
            serverSocket.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}