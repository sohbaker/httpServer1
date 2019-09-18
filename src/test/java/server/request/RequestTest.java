package server.request;

import org.junit.*;

import static org.junit.Assert.*;

public class RequestTest {
    private Request request;
    private String method;
    private String path;
    private String host;
    private String body;

    @Before
    public void setUpDummy() {
        method = "GET";
        path = "/simple_get";
        host = "localhost:3000";
        body = "body text";
        request = new Request(method, path, host, body);
    }

    @Test
    public void returnsTheRequestMethod() {
        assertEquals(method, request.getMethod());
    }

    @Test
    public void returnsTheRequestPath() {
        assertEquals(path, request.getPath());
    }

    @Test
    public void returnsTheRequestHost() {
        assertEquals(host, request.getHost());
    }

    @Test
    public void returnsTheRequestBody() {
        assertEquals(body, request.getBody());
    }
}