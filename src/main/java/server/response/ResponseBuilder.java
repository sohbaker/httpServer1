package server.response;

import java.util.List;

public class ResponseBuilder {
    public Response build(StatusCode statusCode, List<String> headersList, String body) {
        return new Response()
                .setStatusLine(statusCode)
                .setHeaders(headersList)
                .setBody(body);
    }
}