import java.util.*;

public class RequestMatcher {
    public String getResponseStatusFor(RequestLine requestLine) {
        String responseStatus;
        switch (requestLine) {
            case SIMPLE_GET:
            case SIMPLE_HEAD:
            case HEAD_NO_BODY:
            case OPTIONS_GET:
            case OPTIONS_2_GET:
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

    public List<String> getResponseHeadersFor(RequestLine requestLine) {
        List<String> headers = new ArrayList<>();
        switch (requestLine) {
            case OPTIONS_2_GET:
                headers.add("Allow: " + Method.GET.toString() + ", " + Method.HEAD.toString() + ", " + Method.OPTIONS.toString() + ", " + Method.PUT.toString() + ", " + Method.POST.toString());
                headers.add("Date: " + new DateHeader().getDate());
                break;
            case OPTIONS_GET:
                headers.add("Allow: " + Method.GET.toString() + ", " + Method.HEAD.toString() + ", " + Method.OPTIONS.toString());
                headers.add("Date: " + new DateHeader().getDate());
                break;
            default:
                headers.add("Date: " + new DateHeader().getDate());
        }
        return headers;
    }
}