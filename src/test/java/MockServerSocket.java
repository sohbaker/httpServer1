import java.io.IOException;
import java.net.*;

public class MockServerSocket extends ServerSocket {
    private Socket mockClientSocket;
    private boolean acceptCalled = false;

    public MockServerSocket(Socket mockClientSocket) throws IOException {
        this.mockClientSocket = mockClientSocket;
    }

    @Override
    public Socket accept() {
        this.acceptCalled = true;
        return this.mockClientSocket;
    }

    public boolean wasAcceptCalled() {
        return this.acceptCalled;
    }
}
