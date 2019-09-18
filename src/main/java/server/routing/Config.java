package server.routing;

import server.constants.Path;
import server.constants.StatusCode;
import server.constants.Method;
import server.response.*;

public class Config {
    public Routes setRoutes() {
        return new Routes()
                .add(routeWithDefaultHandler(Method.GET, Path.SIMPLE_GET.getPath()))
                .add(routeWithDefaultHandler(Method.HEAD, Path.SIMPLE_GET.getPath()))
                .add(routeWithDefaultHandler(Method.HEAD, Path.GET_WITH_BODY.getPath()))
                .add(routeWithDefaultHandler(Method.OPTIONS, Path.GET_WITH_BODY.getPath()))
                .add(routeWithDefaultHandler(Method.GET, Path.OPTIONS_ONE.getPath()))
                .add(routeWithDefaultHandler(Method.HEAD, Path.OPTIONS_ONE.getPath()))
                .add(routeWithDefaultHandler(Method.OPTIONS, Path.OPTIONS_ONE.getPath()))
                .add(routeWithDefaultHandler(Method.GET, Path.OPTIONS_TWO.getPath()))
                .add(routeWithDefaultHandler(Method.HEAD, Path.OPTIONS_TWO.getPath()))
                .add(routeWithDefaultHandler(Method.OPTIONS, Path.OPTIONS_TWO.getPath()))
                .add(routeWithDefaultHandler(Method.PUT, Path.OPTIONS_TWO.getPath()))
                .add(routeWithDefaultHandler(Method.POST, Path.OPTIONS_TWO.getPath()))
                .add(routeWithDefaultHandler(Method.POST, Path.ECHO_BODY.getPath()))
                .add(routeWithRedirectHandler(Method.GET, Path.REDIRECT.getPath()));
    }

    private Route routeWithDefaultHandler(Method method, String path) {
        return RouteBuilder.build(method, path, (request) -> new ResponseBuilder().build(StatusCode._200, null, null, request.getBody()));
    }

    private Route routeWithRedirectHandler(Method method, String path) {
        String scheme = "http";
        String redirectTo = Path.SIMPLE_GET.getPath();
        return RouteBuilder.build(method, path, (request) -> new ResponseBuilder().build(StatusCode._301, "Location", new URL(scheme, request.getHost(), redirectTo).build(), request.getBody()));
    }
}