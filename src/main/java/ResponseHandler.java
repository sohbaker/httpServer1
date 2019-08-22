public class ResponseHandler {
    private Request request;
    private RequestMatcher matcher;

    public ResponseHandler(Request request, RequestMatcher matcher) {
        this.request = request;
        this.matcher = matcher;
    }

    private String getEnumNameForRequestLine() {
        String requestLine = request.getFirstLine();

        for (RequestLine line : RequestLine.values()) {
            if (requestLine.contains(line.getLine())) {
                return line.name();
            }
        }
        return RequestLine.INVALID.toString();
    }

    public String getResponseStatusCode() {
        return matcher.getResponseStatusFor(RequestLine.valueOf(getEnumNameForRequestLine()));
    }
}