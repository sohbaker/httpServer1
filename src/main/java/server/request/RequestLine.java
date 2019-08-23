package server.request;

public enum RequestLine {
    SIMPLE_GET("GET /simple_get"),
    NOT_FOUND("GET /not_found_resource"),
    GET_REDIRECT("GET /redirect"),
    SIMPLE_HEAD("HEAD /simple_get"),
    HEAD_NO_BODY("HEAD /get_with_body"),
    GET_ONLY_HEAD("GET /get_with_body"),
    OPTIONS_2_GET("OPTIONS /method_options2"),
    OPTIONS_GET("OPTIONS /method_options"),
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