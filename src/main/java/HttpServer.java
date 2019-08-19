import java.io.*;
import java.net.*;

public class HttpServer {
    private ServerSocket serverSocket;
    private InputStream clientInput;
    private PrintWriter clientOutput;
    private PrintWriter output;

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
            output.println("Accepted Client Connection");
            setUpIOStreams();
            clientOutput.printf(sendResponseFor(request()));
            clientSocket.close();
        } catch (IOException ex) {
            output.println(ex);
        }
    }

    private String request() throws IOException {
        StringBuilder result = new StringBuilder();
        do {
            result.append((char) clientInput.read());
        } while (clientInput.available() > 0);
        return result.toString();
    }

    private String sendResponseFor(String request) {
        Routes routes = new Routes();
        Response response = new Response();

        if (routes.isGetRequest(request)) {
            return response.simpleGet();
        } else if (routes.isNotFound(request)) {
            return response.notFound();
        } else if (routes.isPostRequest(request)) {
            return response.echoResponse(request);
        } else {
            return "invalid request";
        }
    }

    private void setUpIOStreams() {
        try {
            clientInput = clientSocket.getInputStream();
            clientOutput = new PrintWriter(clientSocket.getOutputStream(), true);
            output.println("IO streams created");
        } catch (IOException ex) {
            output.println(ex);
        }
    }
}