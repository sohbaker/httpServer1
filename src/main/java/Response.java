import java.util.List;

public class Response {
    private String protocol = Protocol._1_1.getVersion();
    private String statusCode;
    private List<String> headers;
    private String body;
    private ResponseBuilder responseBuilder;

    public Response(String statusCode, List headers, String body, ResponseBuilder responseBuilder) {
        this.statusCode = statusCode;
        this.headers = headers;
        this.body = body;
        this.responseBuilder = responseBuilder;
    }

    public String format() {
        responseBuilder.setStatusLine(protocol, statusCode);
        responseBuilder.setHeaders(headers);
        responseBuilder.setBody(body);

        return responseBuilder.build().toString();
    }
}