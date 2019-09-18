package server.routing;

import server.constants.*;
import server.response.*;

public class Config {
    public Routes setRoutes() {
        return new Routes()
                .add(routeWithDefaultHandler(Method.GET, Path.SIMPLE_GET))
                .add(routeWithDefaultHandler(Method.HEAD, Path.SIMPLE_GET))
                .add(routeWithDefaultHandler(Method.HEAD, Path.GET_WITH_BODY))
                .add(routeWithDefaultHandler(Method.OPTIONS, Path.GET_WITH_BODY))
                .add(routeWithDefaultHandler(Method.GET, Path.OPTIONS_ONE))
                .add(routeWithDefaultHandler(Method.HEAD, Path.OPTIONS_ONE))
                .add(routeWithDefaultHandler(Method.OPTIONS, Path.OPTIONS_ONE))
                .add(routeWithDefaultHandler(Method.GET, Path.OPTIONS_TWO))
                .add(routeWithDefaultHandler(Method.HEAD, Path.OPTIONS_TWO))
                .add(routeWithDefaultHandler(Method.OPTIONS, Path.OPTIONS_TWO))
                .add(routeWithDefaultHandler(Method.PUT, Path.OPTIONS_TWO))
                .add(routeWithDefaultHandler(Method.POST, Path.OPTIONS_TWO))
                .add(routeWithDefaultHandler(Method.POST, Path.ECHO_BODY))
                .add(routeWithRedirectHandler(Method.GET, Path.REDIRECT));
    }

    private Route routeWithDefaultHandler(Method method, Path path) {
        return RouteBuilder.build(method, path, (request) -> new ResponseBuilder().build(StatusCode._200, null, null, request.getBody()));
    }

    private Route routeWithRedirectHandler(Method method, Path path) {
        String scheme = "http";
        String redirectTo = Path.SIMPLE_GET.getPath();
        return RouteBuilder.build(method, path, (request) -> new ResponseBuilder().build(StatusCode._301, "Location", new URL(scheme, request.getHost(), redirectTo).build(), request.getBody()));
    }
}