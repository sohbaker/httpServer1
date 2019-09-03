package server.route;

import server.request.Request;
import server.response.Response;

@FunctionalInterface
public interface RequestHandler {
    Response handle(Request request);
}