package server.response;

import server.request.*;

import java.util.List;

public class ResponseHandler {
    private Request request;
    private RequestMatcher matcher;

    public ResponseHandler(Request request, RequestMatcher matcher) {
        this.request = request;
        this.matcher = matcher;
    }

    public String getResponseStatus() {
        return matcher.getResponseStatusFor(RequestLine.valueOf(getResponseEnumNameForRequestLine()));
    }

    public List<String> getResponseHeaders() {
        return matcher.getResponseHeadersFor(RequestLine.valueOf(getResponseEnumNameForRequestLine()));
    }

    private String getResponseEnumNameForRequestLine() {
        String requestLine = request.getFirstLine();

        for (RequestLine line : RequestLine.values()) {
            if (requestLine.contains(line.getLine())) {
                return line.name();
            }
        }
        return RequestLine.INVALID.toString();
    }
}