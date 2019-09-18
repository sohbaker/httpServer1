package server.routing;

import org.junit.*;
import server.constants.*;

import static org.junit.Assert.*;

public class RouteBuilderTest {
    @Test
    public void buildsANewRouteSetWithGivenValues() {
        Method requestMethod = Method.GET;
        Path requestPath = Path.SIMPLE_GET;
        RequestHandler handler = request -> null;

        Route route = RouteBuilder.build(requestMethod, requestPath, handler);

        assertEquals(requestMethod, route.getRequestMethod());
        assertEquals(requestPath.getPath(), route.getRequestPath());
        assertEquals(handler, route.getHandler());
    }
}