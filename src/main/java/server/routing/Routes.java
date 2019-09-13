package server.routing;

import java.util.*;

public class Routes {
    private Map<String, ArrayList<Route>> activePathsAndRoutes = new HashMap<>();

    public Routes add(Route route) {
        if (isExistingPath(route.getRequestPath())) {
            addRouteToExistingPath(route);
        } else {
            createNewPath(route);
        }
        return this;
    }

    public boolean isExistingPath(String requestPath) {
        for (String key : activePathsAndRoutes.keySet())
            if (key.equals(requestPath)) {
                return true;
            }
        return false;
    }

    public Route getASingleRoute(String requestPath, String requestMethod) {
        List<Route> routes = activePathsAndRoutes.get(requestPath);

        for (Route route : routes) {
            if (route.getRequestMethod().toString().equals(requestMethod)) {
                return route;
            }
        }
        return null;
    }

    public List<String> getValidMethods(String requestPath) {
        List<String> validMethods = new ArrayList<>();
        List<Route> routesForPath = activePathsAndRoutes.get(requestPath);
        for (Route route : routesForPath) {
            validMethods.add(route.getRequestMethod().toString());
        }
        return validMethods;
    }

    private void addRouteToExistingPath(Route route) {
        String key = route.getRequestPath();
        activePathsAndRoutes.get(key).add(route);
    }

    private void createNewPath(Route route) {
        String key = route.getRequestPath();
        ArrayList<Route> routeListForPath = new ArrayList<>();

        routeListForPath.add(route);
        activePathsAndRoutes.put(key, routeListForPath);
    }
}