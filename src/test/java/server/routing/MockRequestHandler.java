package server.routing;

import server.request.Request;
import server.response.Response;

public class MockRequestHandler implements RequestHandler {
    private Response response;

    public MockRequestHandler(Response response) {
        this.response = response;
    }

    @Override
    public Response handle(Request request) {
        return this.response;
    }
}
