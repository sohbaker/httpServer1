import java.io.*;
import java.net.Socket;

public class MockClientSocket extends Socket {
    private ByteArrayInputStream inputStream;
    private ByteArrayOutputStream outputStream;

    public MockClientSocket(ByteArrayInputStream inputStream, ByteArrayOutputStream outputStream) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }

    @Override
    public ByteArrayInputStream getInputStream() {
        return this.inputStream;
    }

    @Override
    public ByteArrayOutputStream getOutputStream() {
        return this.outputStream;
    }
}