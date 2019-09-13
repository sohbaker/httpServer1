package server.routing;

import server.request.Request;

public class URL {
    private String scheme;
    private String host;
    private String path;
    private Request request;

    public URL(Request request) {
        this.request = request;
    }

    public URL setScheme(String scheme) {
        this.scheme = scheme + "://";
        return this;
    }

    public URL setHost(String host) {
        this.host = host;
        return this;
    }

    public URL setPath(String path) {
        this.path = path;
        return this;
    }

    public String toString() {
        return this.scheme + this.host + this.path;
    }
}