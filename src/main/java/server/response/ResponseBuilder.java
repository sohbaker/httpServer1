package server.response;

import server.constants.StatusCode;

public class ResponseBuilder {
    public Response build(StatusCode statusCode, String headerName, String headerValue, String body) {
        return new Response()
                .setStatusLine(statusCode)
                .setHeaders(headerName, headerValue)
                .setBody(body);
    }
}