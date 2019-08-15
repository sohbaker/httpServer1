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
    public void setUpServer() throws IOException {
        ByteArrayInputStream clientInput = new ByteArrayInputStream((fakeGetRequest).getBytes());
        ByteArrayOutputStream clientOutput = new ByteArrayOutputStream();
        mockClientSocket = new MockClientSocket(clientInput, clientOutput);
        MockServerSocket mockServerSocket = new MockServerSocket(mockClientSocket);
        server = new HttpServer(mockServerSocket, serverMessages);
    }

    @Test
    public void sendsAnEmptyResponseWithStatusCode200ForSimpleGetRequest() throws IOException {
        server.communicate();
        assertThat(mockClientSocket.getOutputStream().toString(), containsString("200"));
    }
}