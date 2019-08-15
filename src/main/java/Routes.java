public class Routes {
    public boolean isGetRequest(String request) {
        return request.equalsIgnoreCase("GET /simple_get HTTP/1.1");
    }

    public boolean isNotFound(String request) {
        return request.equalsIgnoreCase("GET /not_found_resource HTTP/1.1");
    }
}