public class Routes {
    private boolean isGetRequest(String request) {
        return request.equalsIgnoreCase("GET /simple_get HTTP/1.1");
    }

    public boolean isValidRoute(String request) {
        return isGetRequest(request);
    }
}
