package server.response;

public enum StatusCode {
    _200("200 OK"),
    _301("301 Moved Permanently"),
    _400("400 Bad Request"),
    _404("404 Not Found"),
    _405("405 server.request.Method Not Allowed");

    public final String message;

    StatusCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}