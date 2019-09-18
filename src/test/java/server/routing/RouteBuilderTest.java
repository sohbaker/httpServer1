package server.routing;

import org.junit.*;
import server.constants.Method;

import static org.junit.Assert.*;

public class RouteBuilderTest {
    @Test
    public void buildsANewRouteSetWithGivenValues() {
        Method requestMethod = Method.GET;
        String requestPath = "/hello";
        RequestHandler handler = request -> null;

        Route route = RouteBuilder.build(requestMethod, requestPath, handler);

        assertEquals(requestMethod, route.getRequestMethod());
        assertEquals(requestPath, route.getRequestPath());
        assertEquals(handler, route.getHandler());
    }
}