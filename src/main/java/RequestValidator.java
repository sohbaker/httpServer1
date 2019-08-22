public class RequestValidator {
    public boolean isValidMethod(String requestMethod) {
        for (Method method : Method.values()) {
            if (method.toString().equalsIgnoreCase(requestMethod)) {
                return true;
            }
        }
        return false;
    }

    public boolean isValidRequestLine(String requestLine) {
        for (RequestLine line : RequestLine.values()) {
            if (requestLine.equalsIgnoreCase(line.getLine())) {
                return true;
            }
        }
        return false;
    }

    public boolean isValidRequest(String requestMethod, String requestLine) {
        return isValidMethod(requestMethod) && isValidRequestLine(requestLine);
    }
}