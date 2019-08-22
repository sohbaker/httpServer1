import org.junit.*;

import static org.junit.Assert.*;

public class ResponseBuilderTest {
    ResponseBuilder responseBuilder = new ResponseBuilder();

    @Test
    public void build() {
        responseBuilder.setStatusLine("HTTP/1.1", "200 OK", new ControlCharacter().CRLF());
        responseBuilder.setBody("");

        assertEquals(responseBuilder.build().toString(),"HTTP/1.1 200 OK\r\n\r\n");
    }
}