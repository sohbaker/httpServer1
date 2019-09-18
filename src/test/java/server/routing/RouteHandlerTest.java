package server.routing;

import org.junit.*;
import server.constants.StatusCode;
import server.request.*;
import server.response.*;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

public class RouteHandlerTest {
    private RouteHandler routeHandler;
    private Response response;

    @Before
    public void setUpFakes() {
        Routes routes = new Config().setRoutes();
        routeHandler = new RouteHandler(routes);
    }

    @Test
    public void returnsEmptyResponseWithStatusCode200ForSimpleGetRequest() {
        String getRequest = "GET /simple_get HTTP/1.1\r\n\r\n";
        Request fakeGetRequest  = new RequestParser(getRequest).buildRequest();

        response = routeHandler.getResponse(fakeGetRequest);

        assertThat(response.toString(), containsString(StatusCode._200.getMessage()));
    }

    @Test
    public void returnsEchoedResponseWithStatusCode200ForSimplePostRequest() {
        String postRequest = "POST /echo_body HTTP/1.1\r\n\r\nsome body";
        Request fakePostRequest  = new RequestParser(postRequest).buildRequest();

        response = routeHandler.getResponse(fakePostRequest);

        assertThat(response.toString(), containsString(StatusCode._200.getMessage()));
        assertThat(response.toString(), containsString("some body"));
    }

    @Test
    public void returnsResponseWithStatusCode400ForABadRequest() {
        String badRequest = "INVALID /simple_get HTTP/1.1\r\n\r\n";
        Request fakeBadRequest  = new RequestParser(badRequest).buildRequest();

        response = routeHandler.getResponse(fakeBadRequest);

        assertThat(response.toString(), containsString(StatusCode._400.getMessage()));
    }

    @Test
    public void returnsResponseWithStatusCode404ForNotFoundRequest() {
        String notFoundRequest = "OPTIONS /fake_path HTTP/1.1\r\n\r\n";
        Request fakeNotFoundRequest  = new RequestParser(notFoundRequest).buildRequest();

        response = routeHandler.getResponse(fakeNotFoundRequest);

        assertThat(response.toString(), containsString(StatusCode._404.getMessage()));
    }

    @Test
    public void returnsResponseWithStatusCode405WhenRequestMethodIsInvalidForPath() {
        String invalidPathRequest = "OPTIONS /simple_get HTTP/1.1\r\n\r\n";
        Request fakeInvalidPathRequest  = new RequestParser(invalidPathRequest).buildRequest();

        response = routeHandler.getResponse(fakeInvalidPathRequest);

        assertThat(response.toString(), containsString(StatusCode._405.getMessage()));
    }

    @Test
    public void returnsAResponseWithAllowedMethodsHeaderForAnOptionsRequest() {
        String optionsRequest = "OPTIONS /method_options HTTP/1.1\r\n\r\n";
        Request fakeOptionsRequest  = new RequestParser(optionsRequest).buildRequest();

        response = routeHandler.getResponse(fakeOptionsRequest);

        assertThat(response.toString(), containsString(StatusCode._200.getMessage()));
        assertThat(response.toString(), containsString("Allow"));
        assertThat(response.toString(), containsString("GET, HEAD, OPTIONS"));
    }

    @Test
    public void returnsAResponseWithNoBodyForAHeadRequest() {
        String headRequest = "HEAD /get_with_body HTTP/1.1\r\n\r\n";
        Request fakeHeadRequest  = new RequestParser(headRequest).buildRequest();

        response = routeHandler.getResponse(fakeHeadRequest);
        String[] splitResponse = response.toString().split("\r\n\r\n");

        assertThat(response.toString(), containsString(StatusCode._200.getMessage()));
        assertEquals(1, splitResponse.length);
    }
}