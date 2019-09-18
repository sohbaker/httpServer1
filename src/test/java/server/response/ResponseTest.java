package server.response;

import org.junit.*;

import static org.junit.Assert.*;

public class ResponseTest {
    @Test
    public void returnsAResponseStringSetWithGivenValues() {
        Response response = new Response();
        Response result = response.setStatusLine(StatusCode._200).setHeaders("Test", "this is a header").setBody("hello");
        assertEquals("HTTP/1.1 200 OK\r\nTest: this is a header\r\n\r\nhello", result.toString());
    }
}