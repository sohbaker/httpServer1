package server.routing;

public class URL {
    private String scheme;
    private String host;
    private String path;

    public URL(String scheme, String host, String path) {
        this.scheme = setScheme(scheme);
        this.host = host;
        this.path = path;
    }

    public String build() {
        return this.scheme + this.host + this.path;
    }

    private String setScheme(String scheme) {
        return scheme + "://";
    }
}