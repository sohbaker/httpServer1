public enum StatusCode {
    _200("200 OK"),
    _400("400 Bad Request"),
    _404("404 Not Found");

    public final String message;

    StatusCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}