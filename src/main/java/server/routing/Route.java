package server.routing;

import server.request.Method;

public class Route {
    private Method method;
    private String requestPath;
    private FunctionalHandler handler;

    public Route setMethod(Method method) {
        this.method = method;
        return this;
    }

    public Route setPath(String path) {
        this.requestPath = path;
        return this;
    }

    public Route setHandler(FunctionalHandler handler) {
        this.handler = handler;
        return this;
    }

    public Method getMethod() {
        return this.method;
    }

    public String getPath(){
        return this.requestPath;
    }

    public FunctionalHandler getHandler() {
        return this.handler;
    }
}