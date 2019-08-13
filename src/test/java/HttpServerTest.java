import org.junit.*;
import java.io.*;
import java.net.*;

import static org.junit.Assert.assertTrue;

public class HttpServerTest {
        private MockServerSocket mockServerSocket;
        private HttpServer server;
        
        @Before
        public void setUpServer() throws IOException {
             String fakeInput = "GET / HTTP/1.1";
             ByteArrayInputStream clientInput = new ByteArrayInputStream((fakeInput).getBytes());
             ByteArrayOutputStream clientOutput = new ByteArrayOutputStream();
             Socket mockClientSocket = new MockClientSocket(clientInput, clientOutput);
             mockServerSocket = new MockServerSocket(mockClientSocket);
             server = new HttpServer(mockServerSocket);
        }

        @Test
        public void acceptsANewClientConnection() {
            server.listen();
            assertTrue(mockServerSocket.wasAcceptCalled());
        }
}