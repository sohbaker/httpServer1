public enum RequestLine {
    SIMPLE_GET("GET /simple_get"),
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