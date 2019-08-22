public class RequestValidator {
    private boolean isValidMethod(String requestMethod) {
        for (Method method : Method.values()) {
            if (method.toString().equalsIgnoreCase(requestMethod)) {
                return true;
            }
        }
        return false;
    }

    private boolean isValidRequestLine(String requestLine) {
        for (RequestLine line : RequestLine.values()) {
            if (requestLine.contains(line.getLine())) {
                return true;
            }
        }
        return false;
    }

    public boolean isValidRequest(String requestMethod, String requestLine) {
        return isValidMethod(requestMethod) && isValidRequestLine(requestLine);
    }
}