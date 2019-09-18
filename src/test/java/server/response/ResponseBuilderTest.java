package server.response;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

public class ResponseBuilderTest {
    @Test
    public void returnsAResponse() {
        ResponseBuilder responseBuilder = new ResponseBuilder();

        Response response = responseBuilder.build(StatusCode._200, "Test", "has a header", "and body");

        assertEquals("HTTP/1.1 200 OK\r\nTest: has a header\r\n\r\nand body", response.toString());
    }
}