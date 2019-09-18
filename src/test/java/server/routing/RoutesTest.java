package server.routing;

import org.junit.*;
import server.constants.*;
import server.response.*;

import java.util.*;

import static org.junit.Assert.*;

public class RoutesTest {
    private Routes routes;
    private MockRequestHandler requestHandler;
    private Map<String, ArrayList<Route>> allRoutesAndPaths;
    private List<Route> routesForSinglePath;

    @Before
    public void setUp() {
        routes = new Routes();
        Response fakeResponse = new ResponseBuilder().build(StatusCode._200, null, null, null);
        requestHandler = new MockRequestHandler(fakeResponse);
    }

    @Test
    public void addsANewRouteWhenThePathDoesNotExist() {
        Route mockRoute1 = RouteBuilder.build(Method.GET, Path.SIMPLE_GET, requestHandler);

        routes.add(mockRoute1);

        String path = Path.SIMPLE_GET.getPath();
        allRoutesAndPaths = routes.getAllPathsAndRoutes();

        assertTrue(allRoutesAndPaths.containsKey(path));
        assertEquals(1, allRoutesAndPaths.size());
    }

    @Test
    public void addsARouteToAnExistingPath() {
        Route mockRoute1 = RouteBuilder.build(Method.GET, Path.SIMPLE_GET, requestHandler);
        Route mockRoute2 = RouteBuilder.build(Method.OPTIONS, Path.SIMPLE_GET, requestHandler);

        routes.add(mockRoute1);
        routes.add(mockRoute2);

        String path = Path.SIMPLE_GET.getPath();

        allRoutesAndPaths = routes.getAllPathsAndRoutes();
        routesForSinglePath = routes.getRoutesForPath(path);

        assertTrue(allRoutesAndPaths.containsKey(path));
        assertEquals(1, allRoutesAndPaths.size());
        assertEquals(2, routesForSinglePath.size());
    }

    @Test
    public void doesNotAddARouteIfItAlreadyExists() {
        Route mockRoute1 = RouteBuilder.build(Method.GET, Path.SIMPLE_GET, requestHandler);
        Route mockRoute2 = RouteBuilder.build(Method.OPTIONS, Path.SIMPLE_GET, requestHandler);
        Route mockRoute3 = RouteBuilder.build(Method.OPTIONS, Path.SIMPLE_GET, requestHandler);

        routes.add(mockRoute1);
        routes.add(mockRoute2);
        routes.add(mockRoute3);

        String path = Path.SIMPLE_GET.getPath();

        allRoutesAndPaths = routes.getAllPathsAndRoutes();
        routesForSinglePath = routes.getRoutesForPath(path);

        assertTrue(allRoutesAndPaths.containsKey(path));
        assertEquals(1, allRoutesAndPaths.size());
        assertEquals(2, routesForSinglePath.size());
    }

    @Test
    public void returnsASingleRoute() {
        Route mockRoute1 = RouteBuilder.build(Method.GET, Path.SIMPLE_GET, requestHandler);

        routes.add(mockRoute1);

        String path = Path.SIMPLE_GET.getPath();
        String method = Method.GET.toString();

        assertEquals(mockRoute1, routes.getASingleRoute(path, method));
    }

    @Test
    public void knowsTheValidMethodsForAPath() {
        Route mockRoute1 = RouteBuilder.build(Method.GET, Path.SIMPLE_GET, requestHandler);
        Route mockRoute2 = RouteBuilder.build(Method.OPTIONS, Path.SIMPLE_GET, requestHandler);

        routes.add(mockRoute1);
        routes.add(mockRoute2);

        String path = Path.SIMPLE_GET.getPath();
        List<String> validMethods = routes.getMethodsForPath(path);

        assertTrue(validMethods.contains("GET"));
        assertTrue(validMethods.contains("OPTIONS"));
    }
}