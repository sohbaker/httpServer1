package server.constants;

public enum Path {
    SIMPLE_GET("/simple_get"),
    GET_WITH_BODY("/get_with_body"),
    OPTIONS_ONE("/method_options"),
    OPTIONS_TWO("/method_options2"),
    ECHO_BODY("/echo_body"),
    REDIRECT("/redirect"),;

    public final String path;

    Path(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
