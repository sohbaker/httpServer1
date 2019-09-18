package server.routing;

import server.constants.Method;
import server.constants.StatusCode;
import server.request.*;
import server.response.*;

import java.util.List;

public class RouteHandler {
    private Routes routes;

    public RouteHandler(Routes routes) {
        this.routes = routes;
    }

    public Response getResponse(Request request) {
        if (!isValidPath(request)) {
            return buildNotFoundResponse(request);
        } else if (!isValidMethod(request)) {
            return buildBadRequestResponse(request);
        } else if (!isValidMethodForPath(request)) {
            return buildNotAllowedResponse(request);
        } else if (isOptionsRequest(request)) {
            return buildOptionsResponse(request);
        } else if (isHeadRequest(request)) {
            return buildHeadResponse();
        } else {
            return getRouteHandler(request);
        }
    }

    private boolean isValidPath(Request request) {
        return routes.isExistingPath(request.getPath());
    }

    private Response buildNotFoundResponse(Request request) {
        return new ResponseBuilder().build(StatusCode._404,null, null, request.getBody());
    }

    private boolean isValidMethod(Request request) {
        for (Method method : Method.values()) {
            if (method.toString().equalsIgnoreCase(request.getMethod())) {
                return true;
            }
        }
        return false;
    }

    private Response buildBadRequestResponse(Request request) {
        return new ResponseBuilder().build(StatusCode._400,null, null, request.getBody());
    }

    private boolean isValidMethodForPath(Request request) {
        return routes.getMethodsForPath(request.getPath()).contains(request.getMethod());
    }

    private Response buildNotAllowedResponse(Request request) {
        List<String> validRequestMethods = routes.getMethodsForPath(request.getPath());
        String validMethodsString = String.join(", ", validRequestMethods);

        return new ResponseBuilder().build(StatusCode._405, "Allow", validMethodsString, request.getBody());
    }

    private boolean isOptionsRequest(Request request) {
        return Method.OPTIONS.toString().equalsIgnoreCase(request.getMethod());
    }

    private Response buildOptionsResponse(Request request) {
        List<String> validRequestMethods = routes.getMethodsForPath(request.getPath());
        String validMethodsString = String.join(", ", validRequestMethods);

        return new ResponseBuilder().build(StatusCode._200, "Allow", validMethodsString, request.getBody());
    }

    private boolean isHeadRequest(Request request) {
        return Method.HEAD.toString().equalsIgnoreCase(request.getMethod());
    }

    private Response buildHeadResponse() {
        return new ResponseBuilder().build(StatusCode._200, null, null, null);
    }

    private Response getRouteHandler(Request request) {
        Route route = routes.getASingleRoute(request.getPath(), request.getMethod());
        return route.getHandler().handle(request);
    }
}