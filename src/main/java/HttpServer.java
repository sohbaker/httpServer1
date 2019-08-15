import java.io.*;
import java.net.*;

public class HttpServer {
    private ServerSocket serverSocket;
    private BufferedReader clientInput;
    private PrintWriter clientOutput;
    private PrintWriter output;
    private String request;
    private Socket clientSocket;

    public HttpServer(ServerSocket serverSocket, PrintWriter output) {
        this.serverSocket = serverSocket;
        this.output = output;
    }

    public void start() {
        while (true) {
            communicate();
        }
    }

    public void communicate() {
        try {
            clientSocket = serverSocket.accept();
            setUpIOStreams();
            parseRequestFrom();
            clientOutput.printf(sendResponse());
            clientSocket.close();
        } catch (IOException ex) {
            output.println(ex);
        }
    }

    private void parseRequestFrom() {
        try {
            request = clientInput.readLine();
        } catch (IOException ex) {
            output.println(ex);
        }
    }

    private String sendResponse() {
        Routes routes = new Routes();
        Response response = new Response();
        if (routes.isGetRequest(request)) {
            return response.simpleGet();
        } else if (routes.isNotFound(request)){
            return response.notFound();
        } else {
            return "invalid request";
        }
    }

    private void setUpIOStreams() {
        try {
            clientInput = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            clientOutput = new PrintWriter(clientSocket.getOutputStream(), true);
            output.println("IO streams created");
        } catch (IOException ex) {
            output.println(ex);
        }
    }
}