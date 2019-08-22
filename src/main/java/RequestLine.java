public enum RequestLine {
    SIMPLE_GET("GET /simple_get HTTP/1.1"),
    NOT_FOUND("GET /not_found_resource HTTP/1.1"),
    POST_ECHO("POST /echo_body HTTP/1.1"),
    INVALID("INVALID / HTTP/1.1");

    public final String line;

    RequestLine(String line) {
        this.line = line;
    }

    public String getLine() {
        return line;
    }
}