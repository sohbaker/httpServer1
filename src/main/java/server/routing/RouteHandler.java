package server.routing;

import server.request.*;
import server.response.*;

import java.util.List;

public class RouteHandler {
    private Routes routes;

    public RouteHandler(Routes routes) {
        this.routes = routes;
    }

    public Response getResponse(Request request) {
        String path = request.getPath();
        if (!isValidMethod(request.getMethod()) || !isValidPath(path)) {
            return buildNotFoundResponse(request);
        } else if (!isValidMethodForPath(request)) {
            return buildNotAllowedResponse(request);
        } else if (isOptionsRequest(request)) {
            return buildOptionsResponse(request);
        } else {
            return getRouteHandler(request);
        }
    }

    private boolean isValidMethodForPath(Request request) {
        String path = request.getPath();
        return routes.getValidMethods(path).contains(request.getMethod());
    }

    private Response buildNotAllowedResponse(Request request) {
        List<String> validRequestMethods = routes.getValidMethods(request.getPath());
        String validMethodsString = String.join(", ", validRequestMethods);

        return new ResponseBuilder().build(StatusCode._405, "Allow", validMethodsString, request.getBody());
    }

    private boolean isOptionsRequest(Request request) {
        return Method.OPTIONS.toString().equalsIgnoreCase(request.getMethod());
    }

    private Response buildOptionsResponse(Request request) {
        List<String> validRequestMethods = routes.getValidMethods(request.getPath());
        String validMethodsString = String.join(", ", validRequestMethods);

        return new ResponseBuilder().build(StatusCode._200, "Allow", validMethodsString, request.getBody());
    }

    private boolean isValidMethod(String requestMethod) {
        for (Method method : Method.values()) {
            if (method.toString().equalsIgnoreCase(requestMethod)) {
                return true;
            }
        }
        return false;
    }

    private boolean isValidPath(String path) {
        return routes.isExistingPath(path);
    }

    private Response buildNotFoundResponse(Request request) {
        return new ResponseBuilder().build(StatusCode._404,null, null, request.getBody());
    }

    private Response getRouteHandler(Request request) {
        Route route = routes.getASingleRoute(request.getPath(), request.getMethod());
        return route.getHandler().handle(request);
    }
}