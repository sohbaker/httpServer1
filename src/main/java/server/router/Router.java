package server.router;

import server.request.Request;

public class Router {
    Request request;

    public Router(Request request) {
        this.request = request;
    }
}