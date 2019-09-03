package server.route;

import server.request.Method;

public class Route {
    private Method requestMethod;
    private String requestPath;
    private RequestHandler requestHandler;

    public Route setRequestMethod(Method method) {
        this.requestMethod = method;
        return this;
    }

    public Route setPath(String path) {
        this.requestPath = path;
        return this;
    }

    public Route setHandler(RequestHandler requestHandler) {
        this.requestHandler = requestHandler;
        return this;
    }
}