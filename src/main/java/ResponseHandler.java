public class ResponseHandler {
    private Request request;
    private RequestMatcher matcher;

    public ResponseHandler(Request request, RequestMatcher matcher) {
        this.request = request;
        this.matcher = matcher;
    }

    public String getsEnumName() {
        String requestLine = request.getFirstLine();

        for (RequestLine line : RequestLine.values()) {
            if (line.getLine().equalsIgnoreCase(requestLine)) {
                return line.name();
            }
        }
        return RequestLine.INVALID.toString();
    }

    public String returnsStatusCode() {
        return matcher.getResponseStatusFor(RequestLine.valueOf(getsEnumName()));
    }
}