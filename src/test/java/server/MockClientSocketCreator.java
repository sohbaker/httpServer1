package server;

import java.io.*;
import java.net.Socket;

public class MockClientSocketCreator {
    public Socket createWithInput(String fakeInput) {
        ByteArrayInputStream clientInput = new ByteArrayInputStream((fakeInput).getBytes());
        ByteArrayOutputStream clientOutput = new ByteArrayOutputStream();
        return new MockClientSocket(clientInput, clientOutput);
    }
}
