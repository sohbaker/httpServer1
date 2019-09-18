package server;

import server.request.*;
import server.routing.*;

import java.io.*;
import java.net.*;

class HttpServer {
    private ServerSocket serverSocket;
    private InputStream clientInput;
    private PrintWriter clientOutput;
    private PrintWriter serverMessages;
    private server.request.Request request;
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
            serverMessages.println("Accepted Client connection");
            setUpIOStreams();
            parseRequest();
            clientOutput.printf(sendResponse());
            clientSocket.close();
            serverMessages.println("Client connection closed");
        } catch (IOException ex) {
            serverMessages.println(ex);
        }
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

    private void parseRequest() throws IOException {
        StringBuilder result = new StringBuilder();
        do {
            result.append((char) clientInput.read());
        } while (clientInput.available() > 0);
//        request = new Request().extractDetails(result.toString());
        request = new RequestParser(result.toString()).buildRequest();
    }

    private String sendResponse() {
        Config config = new Config();
        Routes routes = config.setRoutes();
        RouteHandler routeHandler = new RouteHandler(routes);
        return routeHandler.getResponse(request).toString();
    }
}