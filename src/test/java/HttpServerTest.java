import org.junit.*;
import java.io.*;
import java.net.*;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

public class HttpServerTest {
    private MockServerSocket mockServerSocket;
    private HttpServer server;
    private String fakeGetRequest = "GET / HTTP/1.1";
    private PrintWriter serverMessages = new PrintWriter(new StringWriter(), true);

    @Before
    public void setUpServer() throws IOException {
        ByteArrayInputStream clientInput = new ByteArrayInputStream((fakeGetRequest).getBytes());
        ByteArrayOutputStream clientOutput = new ByteArrayOutputStream();
        Socket mockClientSocket = new MockClientSocket(clientInput, clientOutput);
        mockServerSocket = new MockServerSocket(mockClientSocket);
        server = new HttpServer(mockServerSocket, serverMessages);
    }

    @After
    public void closeServer() {
        server.stop();
    }

    @Test
    public void acceptsANewClientConnection() {
        server.listen();
        assertTrue(mockServerSocket.wasAcceptCalled());
    }

    @Test
    public void receivesGetRequest() {
        server.listen();
        String request = server.readInput();
        assertEquals(fakeGetRequest, request);
    }

    @Test
    public void sendsAnEmptyResponseWithStatusCode200() {
        server.listen();
        server.readInput();
        String response = server.sendResponse();
        assertThat(response, containsString("200"));
    }
}