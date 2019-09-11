package server.routing;

import server.request.Method;
import server.response.*;

public class Conf {
    public Routes setRoutes() {
        return new Routes()
                .addRoute(defaultSuccessHandler(Method.GET, "/simple_get"))
                .addRoute(defaultSuccessHandler(Method.HEAD, "simple_get"))
                .addRoute(defaultSuccessHandler(Method.HEAD, "/get_with_body"))
                .addRoute(defaultSuccessHandler(Method.OPTIONS, "/method_options"))
                .addRoute(defaultSuccessHandler(Method.OPTIONS, "/method_options2"))
                .addRoute(defaultSuccessHandler(Method.POST, "/echo_body"))
    }

    private Route defaultSuccessHandler(Method method, String path) {
        return RouteBuilder.build(method, path, (request) -> new ResponseBuilder().build(StatusCode._200, null, request.getBody()));
    }
}