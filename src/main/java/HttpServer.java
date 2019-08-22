import java.io.*;
import java.net.*;

public class HttpServer {
    private ServerSocket serverSocket;
    private InputStream clientInput;
    private PrintWriter clientOutput;
    private PrintWriter serverMessages;
    private Request request;
    private Socket clientSocket;

    public HttpServer(ServerSocket serverSocket, PrintWriter serverMessages) {
        this.serverSocket = serverSocket;
        this.serverMessages = serverMessages;
    }

    public void start() {
        while (true) {
            communicate();
        }
    }

    public void communicate() {
        try {
            clientSocket = serverSocket.accept();
            serverMessages.println("Accepted Client Connection");
            setUpIOStreams();
            parseRequest();
            clientOutput.printf(sendResponse());
            clientSocket.close();
            serverMessages.println("Client connection closed");
        } catch (IOException ex) {
            serverMessages.println(ex);
        }
    }

    private void parseRequest() throws IOException {
<<<<<<< HEAD
        StringBuilder result = new StringBuilder();
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
        String responseStatus = responseHandler.getResponseStatusCode();

        Response response = new Response(responseStatus, request.getBody(), responseBuilder);
        return response.format();
=======
        do {
            result.append((char) clientInput.read());
        } while (clientInput.available() > 0);
    }

    private String sendResponse() {
        Routes routes = new Routes();
        Response response = new Response();
        request = result.toString();

        if (routes.isGetRequest(request)) {
            return response.simpleGet();
        } else if (routes.isNotFound(request)) {
            return response.notFound();
        } else if (routes.isPostRequest(request)) {
            return response.echoResponse(request);
        } else {
            return "invalid request";
        }
>>>>>>> master
    }

    private void setUpIOStreams() {
        try {
            clientInput = clientSocket.getInputStream();
            clientOutput = new PrintWriter(clientSocket.getOutputStream(), true);
            serverMessages.println("IO streams created");
        } catch (IOException ex) {
            serverMessages.println(ex);
        }
    }
}
