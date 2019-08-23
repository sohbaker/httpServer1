package server.response;

import server.helper.Protocol;

import java.util.List;

public class Response {
    private String protocol = Protocol._1_1.getVersion();
    private String statusCode;
    private List headers;
    private String body;
    private ResponseBuilder responseBuilder;

    public Response(String statusCode, List headers, String body, ResponseBuilder responseBuilder) {
        this.statusCode = statusCode;
        this.headers = headers;
        this.body = body;
        this.responseBuilder = responseBuilder;
    }

    public String format() {
        responseBuilder.setStatusLine(protocol, statusCode).setHeaders(headers).setBody(body);

        return responseBuilder.build().toString();
    }
}