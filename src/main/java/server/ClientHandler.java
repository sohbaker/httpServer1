package server;

import server.request.*;
import server.routing.*;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket clientSocket;
    private Messages serverMessages;
    private int clientId;
    private InputStream clientInput;
    private PrintWriter clientOutput;
    private Request request;

    public ClientHandler(Socket clientSocket, Messages messages, int clientId) {
        this.clientSocket = clientSocket;
        this.serverMessages = messages;
        this.clientId = clientId;
    }

    @Override
    public void run() {
        setUpIOStreams();
        communicate();
    }

    private void setUpIOStreams() {
        try {
            clientInput = clientSocket.getInputStream();
            clientOutput = new PrintWriter(clientSocket.getOutputStream(), true);
            serverMessages.declareAcceptedClientConnection(clientId);
        } catch (IOException ex) {
            serverMessages.showIOException(ex);
        }
    }

    private void communicate() {
        parseRequest();
        sendResponse();
        closeClient();
    }

    private void parseRequest() {
        StringBuilder result = new StringBuilder();
        try {
            do {
                result.append((char) clientInput.read());
            } while (clientInput.available() > 0);
            request = new RequestParser(result.toString()).buildRequest();
        } catch (IOException ex) {
            serverMessages.showIOException(ex);
        }
    }

    private void sendResponse() {
        Config config = new Config();
        Routes routes = config.setRoutes();
        RouteHandler routeHandler = new RouteHandler(routes);
        String response = routeHandler.getResponse(request).toString();
        clientOutput.printf(response);
    }

    private void closeClient() {
        try {
            clientInput.close();
            clientOutput.close();
            serverMessages.declareClosingClientConnection(clientId);
            clientSocket.close();
        } catch (IOException ex) {
            serverMessages.showIOException(ex);
        }
    }
}