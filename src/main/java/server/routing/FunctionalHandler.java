package server.routing;

import server.request.Request;
import server.response.Response;

@FunctionalInterface
public interface FunctionalHandler {
    Response handle(Request request);
}