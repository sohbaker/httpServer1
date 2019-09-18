package server;

import org.junit.*;
import java.io.*;
import java.net.Socket;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

public class ClientHandlerTest {

    @Test
    public void receivesAResponseForARequest() throws IOException {
        String fakeRequest = "GET /simple_get HTTP/1.1\r\nHost: localhost:3000\r\nContent-Length: 9\r\n\r\nbody text";
        PrintWriter serverOutput = new PrintWriter(new StringWriter());
        Messages messages = new Messages(serverOutput);
        Socket mockClientSocket = new MockClientSocketCreator().createWithInput(fakeRequest);
        Runnable clientHandler = new ClientHandler(mockClientSocket, messages, 1);

        clientHandler.run();

        assertThat(mockClientSocket.getOutputStream().toString(), containsString("HTTP/1.1 200 OK\r\n\r\n"));
    }
}