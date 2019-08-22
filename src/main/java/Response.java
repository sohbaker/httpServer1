public class Response {
    private String protocol = Protocol._1_1.getVersion();
    private String statusCode;
    private String body;
    private ResponseBuilder responseBuilder;

    public Response(String statusCode, String body, ResponseBuilder responseBuilder) {
        this.statusCode = statusCode;
        this.body = body;
        this.responseBuilder = responseBuilder;
    }

    public String format() {
        String CRLF = "\r\n";
        responseBuilder.setStatusLine(protocol, statusCode, CRLF);
        responseBuilder.setBody(body);

        return responseBuilder.build().toString();
    }

    public String echoResponse(String request) {
        String[] headerBody = request.split("\r\n\r\n");
        return "HTTP/1.1 200 OK\r\n\r\n" + headerBody[headerBody.length - 1];
    }
}