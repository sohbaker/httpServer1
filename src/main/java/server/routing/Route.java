package server.routing;

import server.request.Method;

public class Route {
    private Method requestMethod;
    private String requestPath;
    private FunctionalHandler handler;

    public Route setRequestMethod(Method method) {
        this.requestMethod = method;
        return this;
    }

    public Route setRequestPath(String path) {
        this.requestPath = path;
        return this;
    }

    public Route setHandler(FunctionalHandler handler) {
        this.handler = handler;
        return this;
    }

    public Method getRequestMethod() {
        return this.requestMethod;
    }

    public String getRequestPath(){
        return this.requestPath;
    }

    public FunctionalHandler getHandler() {
        return this.handler;
    }
}