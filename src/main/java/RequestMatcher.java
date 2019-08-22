public class RequestMatcher {
    public String getResponseStatusFor(RequestLine requestLine) {
        String responseStatus;
        switch (requestLine) {
            case SIMPLE_GET:
            case POST_ECHO:
                responseStatus = StatusCode._200.getMessage();
                break;
            case NOT_FOUND:
                responseStatus = StatusCode._404.getMessage();
                break;
            default:
                responseStatus = StatusCode._400.getMessage();
        }
        return responseStatus;
    }
}