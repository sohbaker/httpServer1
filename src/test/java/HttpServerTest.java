import org.junit.*;
import java.io.*;
import java.net.*;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

public class HttpServerTest {
    private HttpServer server;
    private String fakeGetRequest = "GET /simple_get HTTP/1.1";
    private PrintWriter serverMessages = new PrintWriter(new StringWriter(), true);
    private Socket mockClientSocket;

    @Before
    public void setUpServer() {
        ByteArrayInputStream clientInput = new ByteArrayInputStream((fakeGetRequest).getBytes());
        ByteArrayOutputStream clientOutput = new ByteArrayOutputStream();
        mockClientSocket = new MockClientSocket(clientInput, clientOutput);
        server = new HttpServer(serverMessages);
    }

    @Test
    public void acceptsANewClientConnection() {
        server.start();
        int port = 1234;
        try (Socket ableToConnect = new Socket("127.0.0.1", port)) {
            assertTrue("Accepts connection when server socket is listening", ableToConnect.isConnected());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Test
    public void receivesGetRequest() {
        server.start();
        String request = server.parseRequestFrom(mockClientSocket);
        assertEquals(fakeGetRequest, request);
    }

    @Test
    public void sendsAnEmptyResponseWithStatusCode200ForGetRequest() {
        server.start();
        server.parseRequestFrom(mockClientSocket);
        String response = server.sendResponse();
        assertThat(response, containsString("200"));
    }
}