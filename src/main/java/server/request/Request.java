package server.request;

public class Request {
    private String method;
    private String path;
    private String host;
    private String body;

    public Request(String method, String path, String host, String body){
        this.method = method;
        this.path = path;
        this.host = host;
        this.body = body;
    }

    public String getMethod() {
        return this.method;
    }

    public String getPath() {
        return this.path;
    }

    public String getHost() {
        return this.host;
    }

    public String getBody() {
        return this.body;
    }
}