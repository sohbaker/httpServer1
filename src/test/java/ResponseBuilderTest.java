import org.junit.*;
import java.util.*;

import static org.junit.Assert.*;

public class ResponseBuilderTest {
    ResponseBuilder responseBuilder = new ResponseBuilder();

    @Test
    public void buildsASimpleResponseWithNoBody() {
        responseBuilder.setStatusLine("HTTP/1.1", "200 OK");
        responseBuilder.setBody("");

        assertEquals(responseBuilder.build().toString(),"HTTP/1.1 200 OK\r\n\r\n");
    }

    @Test
    public void buildsAResponseWithAllowedHeaders() {
        responseBuilder.setStatusLine("HTTP/1.1", "200 OK");
        responseBuilder.setHeaders(new ArrayList<>(Arrays.asList("Allow: GET, POST")));
        responseBuilder.setBody("");

        assertEquals(responseBuilder.build().toString(),"HTTP/1.1 200 OK\r\nAllow: GET, POST\r\n\r\n");
    }
}