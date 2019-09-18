package server.routing;

import server.constants.Method;
import server.constants.Path;

public class RouteBuilder {
    public static Route build(Method requestMethod, Path requestPath, RequestHandler handler) {
         return new Route()
                 .setRequestMethod(requestMethod)
                 .setRequestPath(requestPath)
                 .setHandler(handler);
    }
}