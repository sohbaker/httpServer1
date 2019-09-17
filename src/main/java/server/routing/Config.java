package server.routing;

import server.request.Method;
import server.response.*;

public class Config {
    public Routes setRoutes() {
        return new Routes()
                .add(routeWithDefaultHandler(Method.GET, "/simple_get"))
                .add(routeWithDefaultHandler(Method.HEAD, "/simple_get"))
                .add(routeWithDefaultHandler(Method.HEAD, "/get_with_body"))
                .add(routeWithDefaultHandler(Method.OPTIONS, "/get_with_body"))
                .add(routeWithDefaultHandler(Method.GET, "/method_options"))
                .add(routeWithDefaultHandler(Method.HEAD, "/method_options"))
                .add(routeWithDefaultHandler(Method.OPTIONS, "/method_options"))
                .add(routeWithDefaultHandler(Method.GET, "/method_options2"))
                .add(routeWithDefaultHandler(Method.HEAD, "/method_options2"))
                .add(routeWithDefaultHandler(Method.OPTIONS, "/method_options2"))
                .add(routeWithDefaultHandler(Method.PUT, "/method_options2"))
                .add(routeWithDefaultHandler(Method.POST, "/method_options2"))
                .add(routeWithDefaultHandler(Method.POST, "/echo_body"))
                .add(routeWithRedirectHandler(Method.GET, "/redirect"));
    }

    private Route routeWithDefaultHandler(Method method, String path) {
        return RouteBuilder.build(method, path, (request) -> new ResponseBuilder().build(StatusCode._200, null, null, request.getBody()));
    }

    private Route routeWithRedirectHandler(Method method, String path) {
        String scheme = "http";
        String redirectTo = "/simple_get";
        return RouteBuilder.build(method, path, (request) -> new ResponseBuilder().build(StatusCode._301, "Location", new URL(scheme, request.getHost(), redirectTo).build(), request.getBody()));
    }
}