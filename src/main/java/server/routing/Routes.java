package server.routing;

import java.util.*;

public class Routes {
    private Map<String, ArrayList<Route>> validPathsAndRoutes = new HashMap<>();

    public Routes add(Route route) {
        if (isExistingPath(route.getRequestPath())) {
            addRouteToExistingPath(route);
        } else {
            createNewPath(route);
        }
        return this;
    }

    public boolean isExistingPath(String requestPath) {
        return validPathsAndRoutes.containsKey(requestPath);
    }

    public Route getASingleRoute(String requestPath, String requestMethod) {
        List<Route> routes = validPathsAndRoutes.get(requestPath);

        for (Route route : routes) {
            if (route.getRequestMethod().toString().equals(requestMethod)) {
                return route;
            }
        }
        return null;
    }

    public List<String> getValidMethodsForPath(String requestPath) {
        List<String> validMethods = new ArrayList<>();
        List<Route> routesForPath = validPathsAndRoutes.get(requestPath);

        for (Route route : routesForPath) {
            validMethods.add(route.getRequestMethod().toString());
        }
        return validMethods;
    }

    private void addRouteToExistingPath(Route route) {
        String key = route.getRequestPath();
        validPathsAndRoutes.get(key).add(route);
    }

    private void createNewPath(Route route) {
        String key = route.getRequestPath();
        ArrayList<Route> routeListForPath = new ArrayList<>();

        routeListForPath.add(route);
        validPathsAndRoutes.put(key, routeListForPath);
    }
}