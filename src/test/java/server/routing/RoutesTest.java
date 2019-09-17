package server.routing;

import org.junit.*;
import server.request.Method;
import server.response.*;

import java.util.*;

import static org.junit.Assert.*;

public class RoutesTest {
    private Routes routes;
    private MockRequestHandler requestHandler;
    private Map<String, ArrayList<Route>> allRoutesAndPaths;
    private List<Route> routesForSinglePath;
    private String fakePath = "/a_path";

    @Before
    public void setUp() {
        routes = new Routes();
        Response fakeResponse = new ResponseBuilder().build(StatusCode._200, null, null, null);
        requestHandler = new MockRequestHandler(fakeResponse);
    }

    @Test
    public void addsANewRouteWhenThePathDoesNotExist() {
        Route mockRoute1 = RouteBuilder.build(Method.GET, fakePath, requestHandler);

        routes.add(mockRoute1);

        allRoutesAndPaths = routes.getAllPathsAndRoutes();

        assertTrue(allRoutesAndPaths.containsKey(fakePath));
        assertEquals(1, allRoutesAndPaths.size());
    }

    @Test
    public void addsARouteToAnExistingPath() {
        Route mockRoute1 = RouteBuilder.build(Method.GET, fakePath, requestHandler);
        Route mockRoute2 = RouteBuilder.build(Method.OPTIONS, fakePath, requestHandler);

        routes.add(mockRoute1);
        routes.add(mockRoute2);

        allRoutesAndPaths = routes.getAllPathsAndRoutes();
        routesForSinglePath = routes.getRoutesForPath(fakePath);

        assertTrue(allRoutesAndPaths.containsKey(fakePath));
        assertEquals(1, allRoutesAndPaths.size());
        assertEquals(2, routesForSinglePath.size());
    }

    @Test
    public void doesNotAddARouteIfItAlreadyExists() {
        Route mockRoute1 = RouteBuilder.build(Method.GET, fakePath, requestHandler);
        Route mockRoute2 = RouteBuilder.build(Method.OPTIONS, fakePath, requestHandler);
        Route mockRoute3 = RouteBuilder.build(Method.OPTIONS, fakePath, requestHandler);

        routes.add(mockRoute1);
        routes.add(mockRoute2);
        routes.add(mockRoute3);

        allRoutesAndPaths = routes.getAllPathsAndRoutes();
        routesForSinglePath = routes.getRoutesForPath(fakePath);

        assertTrue(allRoutesAndPaths.containsKey(fakePath));
        assertEquals(1, allRoutesAndPaths.size());
        assertEquals(2, routesForSinglePath.size());
    }

    @Test
    public void returnsASingleRoute() {
        Route mockRoute1 = RouteBuilder.build(Method.GET, fakePath, requestHandler);

        routes.add(mockRoute1);

        assertEquals(mockRoute1, routes.getASingleRoute(fakePath, Method.GET.toString()));
    }

    @Test
    public void knowsTheValidMethodsForAPath() {
        Route mockRoute1 = RouteBuilder.build(Method.GET, fakePath, requestHandler);
        Route mockRoute2 = RouteBuilder.build(Method.OPTIONS, fakePath, requestHandler);

        routes.add(mockRoute1);
        routes.add(mockRoute2);

        List<String> validMethods = routes.getMethodsForPath(fakePath);

        assertTrue(validMethods.contains("GET"));
        assertTrue(validMethods.contains("OPTIONS"));
    }
}