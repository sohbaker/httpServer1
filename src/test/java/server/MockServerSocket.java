package server;

import java.io.IOException;
import java.net.*;

public class MockServerSocket extends ServerSocket {
    Socket mockClientSocket;

    MockServerSocket(Socket mockClientSocket) throws IOException {
        this.mockClientSocket = mockClientSocket;
    }

    @Override
    public Socket accept() {
        return this.mockClientSocket;
    }
}
