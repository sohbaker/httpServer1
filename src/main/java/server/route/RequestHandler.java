package server.route;

import server.request.Request;
import server.response.Response;

@FunctionalInterface
public interface MethodHandler {
    Response handle(Request request);
}

