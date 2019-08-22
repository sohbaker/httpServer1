import java.io.*;
import java.net.*;

public class HttpServer {
    private ServerSocket serverSocket;
    private InputStream clientInput;
    private PrintWriter clientOutput;
    private PrintWriter output;
    private Request request;
    private StringBuilder result;

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
            parseRequest();
            clientOutput.printf(sendResponse());
            clientSocket.close();
            output.println("Client connection closed");
        } catch (IOException ex) {
            output.println(ex);
        }
    }

    private void parseRequest() throws IOException {
        result = new StringBuilder();
        do {
            result.append((char) clientInput.read());
        } while (clientInput.available() > 0);
        request = new Request(result.toString());
        request.extractDetails();
    }

    private String sendResponse() {
        RequestMatcher requestMatcher = new RequestMatcher();
        ResponseBuilder responseBuilder = new ResponseBuilder();
        ResponseHandler responseHandler = new ResponseHandler(request, requestMatcher);
        String responseStatus = responseHandler.returnsStatusCode();

        Response response = new Response(responseStatus, request.getBody(), responseBuilder);
        return response.format();
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