public class Response {
    public String simpleGet() {
        return "HTTP/1.1 200 OK\r\n\r\n";
    }

    public String notFound() {
        return "HTTP/1.0 404 Not Found\r\n\r\n";
    }
}