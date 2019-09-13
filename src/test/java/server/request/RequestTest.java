package server.request;

import org.junit.*;

import static org.junit.Assert.*;

public class RequestTest {
    private Request request;

    @Before
    public void setUpDummy() {
        String dummyRequest = "GET /simple_get HTTP/1.1\r\n\r\nbody text";
        request = new Request().extractDetails(dummyRequest);
    }

    @Test
    public void returnsTheRequestMethod() {
        assertEquals(request.getMethod(), "GET");
    }

    @Test
    public void returnsTheRequestPath() {
        assertEquals(request.getPath(), "/simple_get");
    }

    @Test
    public void returnsTheRequestBody() {
        assertEquals(request.getBody(), "body text");
    }
}