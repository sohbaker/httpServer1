package server;

import org.junit.*;
import java.io.*;
import java.net.*;
import java.util.concurrent.Executor;

import static org.junit.Assert.*;

public class HttpServerTest {
    @Test
    public void receivesAClientRequestAndReturnsAResponse() throws IOException {
        String fakeRequest = "GET /simple_get HTTP/1.1\r\nContent-Length: 0\r\n\r\n";
        MockClientSocketCreator mockClientSocketCreator = new MockClientSocketCreator();

        Socket mockClientSocket = mockClientSocketCreator.createWithInput(fakeRequest);
        ServerSocket mockServerSocket = new MockServerSocket(mockClientSocket);
        Messages messages = new Messages(new PrintWriter(new StringWriter()));
        Executor executor = new SynchronousExecutor();

        HttpServer server = new HttpServer(mockServerSocket, messages, executor);

        server.listenForConnections();

        assertEquals("HTTP/1.1 200 OK\r\n\r\n", mockClientSocket.getOutputStream().toString());
    }
}