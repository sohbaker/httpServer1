package server.request;

import org.junit.*;

import static junit.framework.TestCase.*;

public class RequestParserTest {
    String request = "GET /simple_get HTTP/1.1\r\nHost: local:3000\r\nContent-Length: 9\r\n\r\nsome text";
    RequestParser requestParser;

    public RequestParserTest() {
    }

    @Before
    public void setUpParser() {
        requestParser = new RequestParser(request);
    }

    @Test
    public void retrievesAnHttpMethod() {
        assertEquals("GET",requestParser.httpMethod());
    }

    @Test
    public void retrievesAUrlPath() {
        assertEquals("/simple_get",requestParser.path());
    }

    @Test
    public void retrievesTheHostDomain() {
        assertEquals("local:3000",requestParser.host());
    }

    @Test
    public void retrievesTheBody() {
        assertEquals("some text",requestParser.body());
    }

    @Test
    public void returnsNullWhenThereIsNoBody() {
        String request = "GET /simple_get HTTP/1.1\r\nHost: local:3000\r\nContent-Length: 0\r\n\r\n";
        RequestParser requestParser = new RequestParser(request);;
        assertNull("local:3000",requestParser.body());
    }
}