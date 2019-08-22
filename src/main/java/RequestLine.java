public enum RequestLine {
    SIMPLE_GET("GET /simple_get"),
    SIMPLE_HEAD("HEAD /simple_get"),
    HEAD_NO_BODY("HEAD /get_with_body"),
    NOT_FOUND("GET /not_found_resource"),
    POST_ECHO("POST /echo_body"),
    INVALID("INVALID /");

    public final String line;

    RequestLine(String line) {
        this.line = line;
    }

    public String getLine() {
        return line;
    }
}