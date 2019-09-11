package server.routing;

import server.request.Method;

public class RouteBuilder {
    public static Route build(Method requestMethod, String requestPath, FunctionalHandler handler) {
         return new Route()
                 .setRequestMethod(requestMethod)
                 .setRequestPath(requestPath)
                 .setHandler(handler);
    }
}