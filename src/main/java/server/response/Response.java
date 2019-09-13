package server.response;

import server.helper.*;

public class Response {
    public String statusLine;
    public String headers;
    public String body;

    public Response setStatusLine(StatusCode statusCode) {
        this.statusLine = protocol + space + statusCode.getMessage() + CRLF;
        return this;
    }

    public Response setHeaders(String name, String value) {
        if (name != null && value != null) {
            this.headers = formatHeaders(name, value);
        } else {
            this.headers = "";
        }
        return this;
    }

    public Response setBody(String body) {
        this.body = CRLF + body;
        return this;
    }

    public String toString() {
        return this.statusLine + this.headers + this.body;
    }

    private String protocol = Protocol._1_1.getVersion();
    private String space = new ControlCharacter().space();
    private String CRLF = new ControlCharacter().CRLF();

    private String formatHeaders(String name, String value) {
        StringBuilder headers = new StringBuilder();
        headers.append(name).append(": ").append(value);
        return headers.toString();
    }
}