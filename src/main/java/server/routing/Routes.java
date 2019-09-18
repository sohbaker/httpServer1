package server.routing;

import server.constants.Method;

import java.util.*;

public class Routes {
    private Map<String, ArrayList<Route>> allPathsAndRoutes = new HashMap<>();

    public Routes add(Route route) {
        if (isExistingPath(route.getRequestPath())) {
            addRouteToExistingPath(route);
        } else {
            createNewPath(route);
        }
        return this;
    }

    public boolean isExistingPath(String requestPath) {
        return allPathsAndRoutes.containsKey(requestPath);
    }

    public Map<String, ArrayList<Route>> getAllPathsAndRoutes() {
        return allPathsAndRoutes;
    }

    public Route getASingleRoute(String requestPath, String requestMethod) {
        List<Route> routes = allPathsAndRoutes.get(requestPath);

        for (Route route : routes) {
            if (route.getRequestMethod().toString().equals(requestMethod)) {
                return route;
            }
        }
        return null;
    }

    public List<Route> getRoutesForPath(String path) {
        return allPathsAndRoutes.get(path);
    }

    public List<String> getMethodsForPath(String requestPath) {
        List<String> validMethods = new ArrayList<>();
        List<Route> routesForPath = allPathsAndRoutes.get(requestPath);

        for (Route route : routesForPath) {
            validMethods.add(route.getRequestMethod().toString());
        }
        return validMethods;
    }

    private void createNewPath(Route route) {
        String key = route.getRequestPath();
        ArrayList<Route> routeListForPath = new ArrayList<>();

        routeListForPath.add(route);
        allPathsAndRoutes.put(key, routeListForPath);
    }

    private void addRouteToExistingPath(Route route) {
        String path = route.getRequestPath();
        Method requestMethod = route.getRequestMethod();

        if (!isExistingRoute(path, requestMethod)) {
            getRoutesForPath(path).add(route);
        }
    }

    private boolean isExistingRoute(String path, Method requestMethod) {
        List<Route> routes = getRoutesForPath(path);

        for(Route route: routes) {
            if(route.getRequestMethod() == requestMethod) {
                return true;
            }
        }
        return false;
    }
}