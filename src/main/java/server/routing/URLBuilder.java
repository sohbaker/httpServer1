package server.routing;

import server.request.Request;

public class URLBuilder {
    public static URL build(Request request, String path) {
        return new URL(request)
                .setScheme("http")
                .setHost(request.getHost())
                .setPath(path);
    }
}
