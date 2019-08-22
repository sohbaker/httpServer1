import org.junit.*;

import static org.junit.Assert.*;

public class RequestTest {
    private Request request;

    @Before
    public void setUpDummy() {
        String dummyRequest = "GET /simple_get HTTP/1.1\r\n\r\nbody text";
        request = new Request(dummyRequest);
    }

    @Test
    public void extractsRequestMethod() {
        request.extractDetails();
        assertEquals(request.getMethod(), "GET");
    }

    @Test
    public void extractsFirstLineOfRequest() {
        request.extractDetails();
        assertEquals(request.getFirstLine(), "GET /simple_get HTTP/1.1");
    }

    @Test
    public void extractsBodyOfRequest() {
        request.extractDetails();
        assertEquals(request.getBody(), "body text");
    }
}