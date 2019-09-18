package server;

import org.junit.*;
import java.io.*;
import java.net.*;

import static org.junit.Assert.*;

public class HttpServerTest {
    @Test
    public void receivesAClientRequestAndReturnsAResponse() throws IOException {
        MockClientSocketCreator mockClientSocketCreator = new MockClientSocketCreator();
        Socket mockClientSocket = mockClientSocketCreator.createWithInput("GET /simple_get HTTP/1.1\r\n\r\nContent-Length: 0");
        ServerSocket mockServerSocket = new MockServerSocket(mockClientSocket);
        PrintWriter serverMessages = new PrintWriter(new StringWriter(), true);

        HttpServer server = new HttpServer(mockServerSocket, serverMessages);

        server.communicate();

        assertEquals("HTTP/1.1 200 OK\r\n\r\n", mockClientSocket.getOutputStream().toString());
    }
}