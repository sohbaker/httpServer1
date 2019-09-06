import org.junit.*;
import server.request.Request;
import server.request.RequestMatcher;
import server.response.ResponseHandler;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

public class ResponseHandlerTest {
    @Test
    public void returnsStatusCodeForValidRequest() {
        String dummyRequest = "GET /simple_get HTTP/1.1\r\n\r\nbody text";
        Request request = new Request(dummyRequest);
        RequestMatcher requestMatcher = new RequestMatcher();
        ResponseHandler responseHandler = new ResponseHandler(request, requestMatcher);

        request.extractDetails();

        assertThat(responseHandler.getResponseStatus(), containsString("200"));
    }

    @Test
    public void returns400ForInValidRequest() {
        String dummyRequest = "INVALID /\r\n\r\n";
        Request request = new Request(dummyRequest);
        RequestMatcher requestMatcher = new RequestMatcher();
        ResponseHandler responseHandler = new ResponseHandler(request, requestMatcher);

        request.extractDetails();

        assertThat(responseHandler.getResponseStatus(), containsString("400"));
    }
}