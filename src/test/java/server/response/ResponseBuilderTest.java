package server.response;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

public class ResponseBuilderTest {
    @Test
    public void returnsAResponse() {
        ResponseBuilder responseBuilder = new ResponseBuilder();

        Response response = responseBuilder.build(StatusCode._200, null, null, "body");
        assertEquals(response.toString(), true, containsString("body"));
    }
}