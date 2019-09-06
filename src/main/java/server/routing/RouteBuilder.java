package server.routing;

import server.request.Method;

public class RouteBuilder {
    public static Route build(Method requestMethod, String requestPath, FunctionalHandler handler) {
         return new Route()
                 .setMethod(requestMethod)
                 .setPath(requestPath)
                 .setHandler(handler);
    }
}